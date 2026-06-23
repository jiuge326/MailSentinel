package com.mailsentinel.data.repository

import com.mailsentinel.core.database.dao.*
import com.mailsentinel.core.network.ConnectionStateManager
import com.mailsentinel.core.network.ImapClient
import com.mailsentinel.core.parser.MimeParser
import com.mailsentinel.data.mapper.AccountMapper
import com.mailsentinel.data.mapper.MessageMapper
import com.mailsentinel.domain.model.*
import com.mailsentinel.domain.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MailRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val folderDao: FolderDao,
    private val messageDao: MessageDao,
    private val attachmentDao: AttachmentDao,
    private val imapClient: ImapClient,
    private val mimeParser: MimeParser,
    private val connectionStateManager: ConnectionStateManager
) : MailRepository {

    override suspend fun addAccount(account: Account): Long = try {
        val entity = AccountMapper.mapToDomain(account)
        accountDao.insert(entity)
    } catch (e: Exception) {
        -1L
    }

    override suspend fun updateAccount(account: Account) = try {
        val entity = AccountMapper.mapToDomain(account)
        accountDao.update(entity)
    } catch (_: Exception) {}

    override suspend fun deleteAccount(accountId: Long) = try {
        accountDao.delete(accountId)
    } catch (_: Exception) {}

    override suspend fun getAllAccounts(): List<Account> = try {
        accountDao.getAll().map { AccountMapper.mapFromEntity(it) }
    } catch (_: Exception) { emptyList() }

    override suspend fun getAccountById(id: Long): Account? = try {
        accountDao.getById(id)?.let { AccountMapper.mapFromEntity(it) }
    } catch (_: Exception) { null }

    override suspend fun syncMessages(accountId: Long, folderName: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val account = getAccountById(accountId)
                if (account == null) return@withContext Result.failure(Exception("账户不存在"))

                connectionStateManager.updateState(accountId, ConnectionState.CONNECTING)
                accountDao.updateConnectionState(accountId, "connecting")

                val result = imapClient.fetchMessages(account, folderName)
                if (result.isFailure) {
                    val error = result.exceptionOrNull()?.message ?: "同步失败"
                    connectionStateManager.updateState(accountId, ConnectionState.ERROR)
                    accountDao.updateConnectionState(accountId, "error", error)
                    return@withContext Result.failure(result.exceptionOrNull()!!)
                }

                // 解析并入库邮件（含正文）
                val messages = result.getOrNull() ?: emptyList()
                val folderEntity = folderDao.getByFullName(accountId, folderName)
                val actualFolderId = if (folderEntity != null) {
                    folderEntity.id
                } else {
                    folderDao.insert(
                        com.mailsentinel.core.database.entity.FolderEntity(
                            accountId = accountId,
                            fullName = folderName,
                            displayName = when (folderName.uppercase()) {
                                "INBOX" -> "收件箱"
                                "SENT" -> "已发送"
                                "DRAFTS" -> "草稿箱"
                                "TRASH" -> "已删除"
                                else -> folderName
                            },
                            unreadCount = 0,
                            totalCount = 0
                        )
                    )
                }

                val messageEntities = messages.mapNotNull { mimeMsg ->
                    try {
                        val (parsedBody, attachments) = mimeParser.parseMessage(mimeMsg as jakarta.mail.internet.MimeMessage)
                            ?: return@mapNotNull null

                        com.mailsentinel.core.database.entity.MessageEntity(
                            accountId = accountId,
                            folderId = actualFolderId,
                            uid = (mimeMsg as? com.sun.mail.imap.IMAPMessage)?.UID ?: mimeMsg.messageNumber.toLong(),
                            messageIdHeader = mimeMsg.messageID,
                            subject = mimeMsg.subject,
                            fromAddress = (mimeMsg.from?.firstOrNull() as? jakarta.mail.internet.InternetAddress)?.address ?: "",
                            fromName = (mimeMsg.from?.firstOrNull() as? jakarta.mail.internet.InternetAddress)?.personal,
                            toAddresses = mimeMsg.getRecipients(jakarta.mail.Message.RecipientType.TO)
                                ?.map { (it as? jakarta.mail.internet.InternetAddress)?.address.toString() }
                                ?.joinToString(",") ?: "",
                            receivedDate = mimeMsg.receivedDate?.time ?: System.currentTimeMillis(),
                            sentDate = mimeMsg.sentDate?.time,
                            previewText = parsedBody.plainText.take(200),
                            bodyHtml = parsedBody.preview,
                            bodyText = parsedBody.plainText,
                            hasAttachments = attachments.isNotEmpty(),
                            size = mimeMsg.size
                        )
                    } catch (_: Exception) { null }
                }

                if (messageEntities.isNotEmpty()) {
                    messageDao.insertAll(messageEntities)
                }

                connectionStateManager.updateState(accountId, ConnectionState.CONNECTED)
                accountDao.updateConnectionState(accountId, "connected")
                Result.success(Unit)
            } catch (e: Exception) {
                connectionStateManager.updateState(accountId, ConnectionState.ERROR)
                accountDao.updateConnectionState(accountId, "error", e.message)
                Result.failure(e)
            }
        }
    }

    override suspend fun getMessages(accountId: Long, folderId: Long, limit: Int, offset: Int): List<MailMessage> = try {
        messageDao.getMessages(accountId, folderId, limit, offset).map { MessageMapper.mapFromEntity(it) }
    } catch (_: Exception) { emptyList() }

    override suspend fun getMessageById(id: Long): MailMessage? = try {
        messageDao.getById(id)?.let { MessageMapper.mapFromEntity(it) }
    } catch (_: Exception) { null }

    override suspend fun markAsRead(messageId: Long) = try {
        messageDao.markAsRead(messageId)
    } catch (_: Exception) {}

    override suspend fun setStarred(messageId: Long, starred: Boolean) = try {
        messageDao.setStarred(messageId, starred)
    } catch (_: Exception) {}

    override fun observeAccounts(): Flow<List<Account>> {
        return accountDao.getAllActive().map { entities ->
            entities.map { AccountMapper.mapFromEntity(it) }
        }
    }

    override fun observeMessages(accountId: Long, folderId: Long): Flow<List<MailMessage>> {
        return messageDao.getMessagesFlow(accountId, folderId).map { entities ->
            entities.map { MessageMapper.mapFromEntity(it) }
        }
    }

    override suspend fun testConnection(account: Account, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val result = imapClient.connect(account, password)
                if (result.isFailure) return@withContext Result.failure(result.exceptionOrNull()!!)
                result.getOrNull()?.close()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun updateAccountPassword(accountId: Long, password: String) {
        try {
            val entity = accountDao.getById(accountId)
            if (entity != null) {
                accountDao.update(entity.copy(password = password))
            }
        } catch (_: Exception) {}
    }
}
