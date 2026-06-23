package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "messages",
    indices = [
        Index(value = ["account_id", "folder_id", "uid"], unique = true),
        Index(value = ["received_date"]),
        Index(value = ["subject"]),
        Index(value = ["from_address"])
    ],
    foreignKeys = [
        ForeignKey(entity = AccountEntity::class, parentColumns = ["id"], childColumns = ["account_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FolderEntity::class, parentColumns = ["id"], childColumns = ["folder_id"], onDelete = ForeignKey.CASCADE)
    ]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @ColumnInfo(name = "folder_id")
    val folderId: Long,
    val uid: Long,  // IMAP UID
    @ColumnInfo(name = "message_id_header")
    val messageIdHeader: String? = null,
    val subject: String? = null,
    @ColumnInfo(name = "from_address")
    val fromAddress: String,
    @ColumnInfo(name = "from_name")
    val fromName: String? = null,
    @ColumnInfo(name = "to_addresses")
    val toAddresses: String,  // JSON array
    @ColumnInfo(name = "cc_addresses")
    val ccAddresses: String? = null,
    @ColumnInfo(name = "received_date")
    val receivedDate: Long,
    @ColumnInfo(name = "sent_date")
    val sentDate: Long? = null,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean = false,
    @ColumnInfo(name = "is_starred")
    val isStarred: Boolean = false,
    @ColumnInfo(name = "has_attachments")
    val hasAttachments: Boolean = false,
    @ColumnInfo(name = "preview_text")
    val previewText: String? = null,
    @ColumnInfo(name = "body_html")
    val bodyHtml: String? = null,
    @ColumnInfo(name = "body_text")
    val bodyText: String? = null,
    val size: Int = 0,
    @ColumnInfo(name = "flags")
    val flags: String? = null
)
