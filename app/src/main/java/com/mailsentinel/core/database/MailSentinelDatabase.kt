package com.mailsentinel.core.database

import androidx.room.*
import com.mailsentinel.core.database.entity.*
import com.mailsentinel.core.database.dao.*

@Database(
    entities = [
        AccountEntity::class,
        FolderEntity::class,
        MessageEntity::class,
        AttachmentEntity::class,
        OcrResultEntity::class,
        ForwardRuleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MailSentinelDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun folderDao(): FolderDao
    abstract fun messageDao(): MessageDao
    abstract fun attachmentDao(): AttachmentDao
    abstract fun ocrResultDao(): OcrResultDao
    abstract fun forwardRuleDao(): ForwardRuleDao
}
