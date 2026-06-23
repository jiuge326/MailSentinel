package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "folders",
    indices = [Index(value = ["account_id", "full_name"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = ["id"],
        childColumns = ["account_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @ColumnInfo(name = "full_name")
    val fullName: String,  // "INBOX", "Sent", etc.
    @ColumnInfo(name = "display_name")
    val displayName: String,
    @ColumnInfo(name = "message_count")
    val messageCount: Int = 0,
    @ColumnInfo(name = "unread_count")
    val unreadCount: Int = 0,
    @ColumnInfo(name = "uid_validity")
    val uidValidity: Long = 0
)
