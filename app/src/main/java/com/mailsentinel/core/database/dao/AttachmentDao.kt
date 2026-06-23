package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.AttachmentEntity

@Dao
interface AttachmentDao {
    @Query("SELECT * FROM attachments WHERE message_id = :messageId")
    suspend fun getByMessage(messageId: Long): List<AttachmentEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(attachments: List<AttachmentEntity>)
    
    @Query("UPDATE attachments SET ocr_result = :result, ocr_processed = 1 WHERE id = :id")
    suspend fun updateOcrResult(id: Long, result: String)
    
    @Query("SELECT * FROM attachments WHERE ocr_processed = 0 AND content_type LIKE 'image%'")
    suspend fun getPendingOcrImages(): List<AttachmentEntity>
}
