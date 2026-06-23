package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "ocr_results",
    indices = [Index(value = ["attachment_id"]), Index(value = ["message_id"])]
)
data class OcrResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "attachment_id")
    val attachmentId: Long? = null,
    @ColumnInfo(name = "message_id")
    val messageId: Long? = null,
    @ColumnInfo(name = "recognized_text")
    val recognizedText: String,
    val language: String = "zh",
    val confidence: Float = 0f,
    @ColumnInfo(name = "source_type")
    val sourceType: String = "offline",  // offline/cloud
    @ColumnInfo(name = "processed_at")
    val processedAt: Long = System.currentTimeMillis()
)
