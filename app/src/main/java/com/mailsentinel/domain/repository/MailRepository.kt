package com.mailsentinel.domain.repository

import com.mailsentinel.domain.model.*

interface MailRepository {
    suspend fun addAccount(account: Account): Long
    suspend fun updateAccount(account: Account)
    suspend fun deleteAccount(accountId: Long)
    suspend fun getAllAccounts(): List<Account>
    suspend fun getAccountById(id: Long): Account?

    suspend fun syncMessages(accountId: Long, folderName: String = "INBOX"): Result<Unit>
    suspend fun getMessages(accountId: Long, folderId: Long, limit: Int = 50, offset: Int = 0): List<MailMessage>
    suspend fun getMessageById(id: Long): MailMessage?
    suspend fun markAsRead(messageId: Long)
    suspend fun setStarred(messageId: Long, starred: Boolean)

    fun observeAccounts(): kotlinx.coroutines.flow.Flow<List<Account>>
    fun observeMessages(accountId: Long, folderId: Long): kotlinx.coroutines.flow.Flow<List<MailMessage>>

    suspend fun testConnection(account: Account): Result<Unit>
}
