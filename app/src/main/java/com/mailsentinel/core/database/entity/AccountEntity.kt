package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "accounts",
    indices = [Index(value = ["email_address"], unique = true)]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "email_address")
    val emailAddress: String,
    @ColumnInfo(name = "display_name")
    val displayName: String,
    @ColumnInfo(name = "imap_host")
    val imapHost: String,
    @ColumnInfo(name = "imap_port")
    val imapPort: Int = 993,
    @ColumnInfo(name = "smtp_host")
    val smtpHost: String,
    @ColumnInfo(name = "smtp_port")
    val smtpPort: Int = 465,
    @ColumnInfo(name = "use_ssl")
    val useSsl: Boolean = true,
    @ColumnInfo(name = "password")
    val password: String? = null,  // 加密存储
    @ColumnInfo(name = "oauth_token")
    val oauthToken: String? = null,
    @ColumnInfo(name = "refresh_token")
    val refreshToken: String? = null,
    @ColumnInfo(name = "token_type")
    val tokenType: String? = null,  // "password" or "oauth2"
    @ColumnInfo(name = "last_sync_time")
    val lastSyncTime: Long = 0,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean = true,
    @ColumnInfo(name = "connection_state")
    val connectionState: String = "disconnected",  // disconnected/connecting/connected/error
    @ColumnInfo(name = "last_error")
    val lastError: String? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)
