package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.OcrResultEntity

@Dao
interface OcrResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: OcrResultEntity)
    
    @Query("SELECT * FROM ocr_results WHERE attachment_id = :attachmentId")
    suspend fun getByAttachment(attachmentId: Long): OcrResultEntity?
    
    @Query("SELECT * FROM ocr_results WHERE message_id = :messageId")
    suspend fun getByMessage(messageId: Long): List<OcrResultEntity>
}
