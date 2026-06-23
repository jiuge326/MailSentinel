package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages WHERE account_id = :accountId AND folder_id = :folderId ORDER BY received_date DESC LIMIT :limit OFFSET :offset")
    suspend fun getMessages(accountId: Long, folderId: Long, limit: Int = 50, offset: Int = 0): List<MessageEntity>
    
    @Query("SELECT * FROM messages WHERE account_id = :accountId AND folder_id = :folderId ORDER BY received_date DESC")
    fun getMessagesFlow(accountId: Long, folderId: Long): Flow<List<MessageEntity>>
    
    @Query("SELECT * FROM messages WHERE uid = :uid AND folder_id = :folderId AND account_id = :accountId")
    suspend fun getByUid(uid: Long, folderId: Long, accountId: Long): MessageEntity?
    
    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getById(id: Long): MessageEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<MessageEntity>)
    
    @Update
    suspend fun update(message: MessageEntity)
    
    @Query("UPDATE messages SET is_read = 1 WHERE id = :id")
    suspend fun markAsRead(id: Long)
    
    @Query("UPDATE messages SET is_starred = :starred WHERE id = :id")
    suspend fun setStarred(id: Long, starred: Boolean)
    
    @Query("DELETE FROM messages WHERE account_id = :accountId AND folder_id = :folderId AND uid = :uid")
    suspend fun deleteByUid(accountId: Long, folderId: Long, uid: Long)
    
    @Query("DELETE FROM messages WHERE received_date < :beforeTimestamp AND is_read = 1")
    suspend fun purgeOldRead(beforeTimestamp: Long)
    
    @Query("SELECT COUNT(*) FROM messages WHERE account_id = :accountId AND folder_id = :folderId AND is_read = 0")
    suspend fun getUnreadCount(accountId: Long, folderId: Long): Int
}
