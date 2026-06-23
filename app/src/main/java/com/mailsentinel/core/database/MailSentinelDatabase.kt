package com.mailsentinel.core.database

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    version = 2,  // 升级版本：新增 action_type 列到 forward_rules 表
    exportSchema = false
)
abstract class MailSentinelDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun folderDao(): FolderDao
    abstract fun messageDao(): MessageDao
    abstract fun attachmentDao(): AttachmentDao
    abstract fun ocrResultDao(): OcrResultDao
    abstract fun forwardRuleDao(): ForwardRuleDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE forward_rules ADD COLUMN action_type TEXT NOT NULL DEFAULT 'forward'")
            }
        }
    }
}
