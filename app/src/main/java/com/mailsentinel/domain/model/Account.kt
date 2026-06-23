package com.mailsentinel.domain.model

enum class ConnectionState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR
}

data class Account(
    val id: Long = 0,
    val emailAddress: String,
    val displayName: String,
    val imapHost: String,
    val imapPort: Int = 993,
    val smtpHost: String,
    val smtpPort: Int = 465,
    val useSsl: Boolean = true,
    val tokenType: String = "password",  // "password" or "oauth2"
    val lastSyncTime: Long = 0,
    val isActive: Boolean = true,
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val lastError: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
