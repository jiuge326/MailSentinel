package com.mailsentinel.domain.model

data class Attachment(
    val id: Long = 0,
    val messageId: Long,
    val filename: String,
    val contentType: String,
    val size: Int = 0,
    val localPath: String? = null,
    val isDownloaded: Boolean = false,
    val ocrResult: String? = null,
    val ocrProcessed: Boolean = false
)
