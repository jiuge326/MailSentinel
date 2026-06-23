package com.mailsentinel.core.network

import com.mailsentinel.domain.model.Account
import javax.mail.*
import javax.mail.event.MessageCountAdapter
import javax.mail.event.MessageCountEvent
import java.util.Properties
import com.sun.mail.imap.IMAPFolder
import com.sun.mail.imap.IMAPStore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ImapClient {

    fun connect(account: Account, password: String): Result<IMAPStore> {
        return try {
            val props = Properties().apply {
                put("mail.store.protocol", "imaps")
                put("mail.imaps.host", account.imapHost)
                put("mail.imaps.port", account.imapPort.toString())
                // SSL 配置
                put("mail.imaps.ssl.enable", "true")
                put("mail.imaps.ssl.trust", "*")
                // 关键：指定 SSL Socket Factory（Android 兼容）
                put("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                put("mail.imaps.socketFactory.fallback", "false")
                put("mail.imaps.socketFactory.port", account.imapPort.toString())
                // 认证配置（QQ 邮箱需要 PLAIN 机制）
                put("mail.imaps.auth", "true")
                put("mail.imaps.auth.mechanisms", "PLAIN")
                put("mail.imaps.sasl.enable", "false")
                // 超时设置
                put("mail.imaps.timeout", "20000")
                put("mail.imaps.connectiontimeout", "20000")
                // 调试
                put("mail.imaps.debug", "true")
            }

            val session = Session.getInstance(props, null)
            session.setDebug(true)

            val store = session.getStore("imaps") as IMAPStore
            // 使用 emailAddress 作为用户名（QQ 邮箱要求完整地址）
            store.connect(account.imapHost, account.emailAddress, password)

            Result.success(store)
        } catch (e: Exception) {
            val detailedMessage = buildErrorMessage(e)
            Result.failure(Exception(detailedMessage, e))
        }
    }

    /**
     * 构建详细的错误信息，包含异常类型、消息和根因
     */
    private fun buildErrorMessage(e: Exception): String {
        val className = e.javaClass.simpleName
        val message = e.message ?: "无详细信息"
        val cause = e.cause?.let { "${it.javaClass.simpleName}: ${it.message}" } ?: ""
        
        return when {
            className.contains("AuthenticationFailed", true) ->
                "认证失败：邮箱服务器拒绝了登录。QQ邮箱请确保使用授权码而非密码（错误详情: $message）"
            className.contains("SSLHandshake", true) ->
                "SSL握手失败：无法建立安全连接（错误详情: $message）"
            className.contains("SocketTimeout", true) ->
                "连接超时：服务器未在30秒内响应（错误详情: $message）"
            className.contains("Connect", true) ->
                "网络连接失败：无法到达服务器（错误详情: $message）"
            className.contains("FolderClosed", true) ->
                "连接被服务器关闭（错误详情: $message）"
            else ->
                "${className}: $message${if (cause.isNotBlank()) " [根因: $cause]" else ""}"
        }
    }

    fun observeNewMessages(account: Account, password: String): Flow<javax.mail.Message> = callbackFlow {
        var store: IMAPStore? = null
        var folder: IMAPFolder? = null

        try {
            val connectResult = connect(account, password)
            if (connectResult.isFailure) {
                close()
                return@callbackFlow
            }

            store = connectResult.getOrNull()
            folder = store?.getFolder("INBOX") as? IMAPFolder
            folder?.open(IMAPFolder.READ_ONLY)

            folder?.addMessageCountListener(object : MessageCountAdapter() {
                override fun messagesAdded(e: MessageCountEvent) {
                    e.messages.forEach { message ->
                        trySend(message)
                    }
                }
            })

            // 保持连接活跃以接收新消息
            while (folder?.isOpen == true) {
                try {
                    folder.idle()
                } catch (e: Exception) {
                    // IDLE 超时或连接中断，尝试重新进入 IDLE
                    try {
                        if (folder?.isOpen == true) {
                            folder.idle()
                        }
                    } catch (_: Exception) {
                        break
                    }
                }
            }

        } catch (e: Exception) {
            close()
        }

        awaitClose {
            try {
                folder?.close(false)
                store?.close()
            } catch (_: Exception) {}
        }
    }

    fun fetchMessages(account: Account, password: String, folderName: String = "INBOX", limit: Int = 50): Result<List<javax.mail.Message>> {
        return try {
            val storeResult = connect(account, password)
            if (storeResult.isFailure) {
                return Result.failure(storeResult.exceptionOrNull() ?: Exception("连接失败"))
            }

            val store = storeResult.getOrNull()
            val folder = store?.getFolder(folderName)
            folder?.open(IMAPFolder.READ_ONLY)

            val messages = folder?.messages ?: emptyArray()
            val end = messages.size
            val start = maxOf(1, end - limit + 1)

            val result = if (start <= end) {
                folder?.getMessages(start, end)?.toList() ?: emptyList()
            } else {
                emptyList()
            }

            folder?.close(false)
            store?.close()

            Result.success(result)
        } catch (e: Exception) {
            val detailedMessage = buildErrorMessage(e)
            Result.failure(Exception(detailedMessage, e))
        }
    }
}
