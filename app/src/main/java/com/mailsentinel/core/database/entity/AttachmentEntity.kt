package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "attachments",
    indices = [Index(value = ["message_id"])],
    foreignKeys = [ForeignKey(
        entity = MessageEntity::class,
        parentColumns = ["id"],
        childColumns = ["message_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AttachmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "message_id")
    val messageId: Long,
    val filename: String,
    @ColumnInfo(name = "content_type")
    val contentType: String,
    val size: Int = 0,
    @ColumnInfo(name = "local_path")
    val localPath: String? = null,
    @ColumnInfo(name = "is_downloaded")
    val isDownloaded: Boolean = false,
    @ColumnInfo(name = "ocr_result")
    val ocrResult: String? = null,
    @ColumnInfo(name = "ocr_processed")
    val ocrProcessed: Boolean = false
)
