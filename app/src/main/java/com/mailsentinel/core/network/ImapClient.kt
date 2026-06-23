package com.mailsentinel.core.network

import android.os.Looper
import com.mailsentinel.domain.model.Account
import javax.mail.*
import javax.mail.event.MessageCountAdapter
import javax.mail.event.MessageCountEvent
import java.util.Properties
import com.sun.mail.imap.IMAPFolder
import com.sun.mail.imap.IMAPStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class ImapClient {

    /**
     * 在主线程调用 connect 会直接返回失败（避免 NetworkOnMainThreadException）
     */
    suspend fun connect(account: Account, password: String): Result<IMAPStore> {
        // 强制切到 IO 线程，避免 StrictMode 的 NetworkOnMainThreadException
        return withContext(Dispatchers.IO) {
            try {
                // 主线程检测（开发期友好提示）
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    return@withContext Result.failure(
                        Exception("禁止在主线程执行网络操作，请使用 withContext(Dispatchers.IO)")
                    )
                }

                val props = Properties().apply {
                    put("mail.store.protocol", "imaps")
                    put("mail.imaps.host", account.imapHost)
                    put("mail.imaps.port", account.imapPort.toString())
                    // SSL 配置（Android 兼容，不指定 socketFactory，让 Jakarta Mail 自动处理）
                    put("mail.imaps.ssl.enable", "true")
                    put("mail.imaps.ssl.trust", "*")
                    // 指定 TLS 协议版本（QQ 邮箱要求 TLSv1.2+）
                    put("mail.imaps.ssl.protocols", "TLSv1.2 TLSv1.3")
                    // 认证配置
                    put("mail.imaps.auth", "true")
                    // 超时设置（Android 网络有时较慢，设 30 秒）
                    put("mail.imaps.timeout", "30000")
                    put("mail.imaps.connectiontimeout", "30000")
                    // 关闭压缩（部分服务器不支持）
                    put("mail.imaps.compression", "false")
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
    }

    /**
     * 构建详细的错误信息，包含异常类型、消息和根因
     */
    private fun buildErrorMessage(e: Exception): String {
        val className = e.javaClass.simpleName
        val message = e.message ?: "无详细信息"
        val cause = e.cause?.let { "${it.javaClass.simpleName}: ${it.message}" } ?: ""

        return when {
            className.contains("NetworkOnMainThread", true) ->
                "网络连接被阻止：请求在主线程执行（Android 禁止）。请稍后重试，如持续出现请联系开发者。"
            className.contains("AuthenticationFailed", true) ||
            message.contains("AUTHENTICATE FAILED", true) ||
            message.contains("Invalid credentials", true) ->
                "认证失败：邮箱服务器拒绝了登录。\nQQ/163邮箱必须使用「授权码」而非登录密码。\n请到邮箱设置→账户→开启IMAP/SMTP并生成授权码。"
            className.contains("SSLHandshake", true) ||
            className.contains("SSLException", true) ->
                "SSL握手失败：无法建立安全连接。\n请确认已开启SSL（端口993），并检查网络代理设置。"
            className.contains("SocketTimeout", true) ||
            message.contains("timed out", true) ->
                "连接超时：服务器未在30秒内响应。\n请检查网络，或切换WiFi/移动数据后重试。"
            className.contains("ConnectException", true) ||
            message.contains("Connection refused", true) ->
                "无法连接服务器：连接被拒绝。\n请确认IMAP服务器地址正确（QQ: imap.qq.com，163: imap.163.com）。"
            className.contains("UnknownHost", true) ||
            message.contains("Unable to resolve host", true) ->
                "无法解析服务器地址：请检查网络连接，确认可以访问互联网。"
            className.contains("FolderClosed", true) ||
            className.contains("StoreClosed", true) ->
                "连接被服务器关闭，将自动重试重连。"
            else ->
                "${className}: ${message}${if (cause.isNotBlank()) "\n[根因: $cause]" else ""}"
        }
    }

    fun observeNewMessages(account: Account, password: String): Flow<javax.mail.Message> = callbackFlow {
        var store: IMAPStore? = null
        var folder: IMAPFolder? = null

        try {
            val connectResult = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                connect(account, password)
            }
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
            val connectResult = kotlinx.coroutines.runBlocking {
                kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                    connect(account, password)
                }
            }
            if (connectResult.isFailure) {
                return Result.failure(connectResult.exceptionOrNull() ?: Exception("连接失败"))
            }

            val store = connectResult.getOrNull()
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
