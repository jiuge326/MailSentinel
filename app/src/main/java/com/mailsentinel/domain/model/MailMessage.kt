package com.mailsentinel.domain.model

data class MailMessage(
    val id: Long = 0,
    val accountId: Long,
    val folderId: Long,
    val uid: Long,
    val messageIdHeader: String? = null,
    val subject: String? = null,
    val fromAddress: String,
    val fromName: String? = null,
    val toAddresses: List<String>,
    val ccAddresses: List<String>? = null,
    val receivedDate: Long,
    val sentDate: Long? = null,
    val isRead: Boolean = false,
    val isStarred: Boolean = false,
    val hasAttachments: Boolean = false,
    val previewText: String? = null,
    val bodyHtml: String? = null,
    val bodyText: String? = null,
    val size: Int = 0,
    val flags: List<String> = emptyList()
)
