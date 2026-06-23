package com.mailsentinel.core.network

import com.mailsentinel.domain.model.Account
import jakarta.mail.*
import jakarta.mail.event.MessageCountAdapter
import jakarta.mail.event.MessageCountEvent
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
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
            put("mail.imaps.ssl.enable", account.useSsl.toString())
            put("mail.imaps.auth", "true")
            put("mail.imaps.timeout", "30000")
            put("mail.imaps.connectiontimeout", "30000")
            // 信任所有证书（用户可选择）
            put("mail.imaps.ssl.trust", "*")
            put("mail.imaps.ssl.checkserveridentity", "false")
        }
        
        val session = Session.getInstance(props, null)
        val store = session.getStore("imaps") as IMAPStore
        
        if (account.tokenType == "oauth2") {
            // OAuth2 认证
            // 需要在 authenticate 前设置 token
            store.connect(account.imapHost, account.emailAddress, password)
        } else {
            store.connect(account.imapHost, account.emailAddress, password)
        }
        
        Result.success(store)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun observeNewMessages(account: Account, password: String): Flow<jakarta.mail.Message> = callbackFlow {
        var store: IMAPStore? = null
        var folder: IMAPFolder? = null
        
        try {
            val connectResult = connect(account, password)
            if (connectResult.isFailure) {
                close(connectResult.exceptionOrNull())
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
                    // IDLE 超时或连接中断，尝试重新连接
                    break
                }
            }
            
        } catch (e: Exception) {
            close(e)
        }
        
        awaitClose {
            try {
                folder?.close(false)
                store?.close()
            } catch (_: Exception) {}
        }
    }
    
    fun fetchMessages(account: Account, password: String, folderName: String = "INBOX", limit: Int = 50): Result<List<jakarta.mail.Message>> {
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
        Result.failure(e)
    }
    }
}
