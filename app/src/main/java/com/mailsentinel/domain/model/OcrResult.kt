package com.mailsentinel.domain.model

data class OcrResult(
    val id: Long = 0,
    val attachmentId: Long? = null,
    val messageId: Long? = null,
    val recognizedText: String,
    val language: String = "zh",
    val confidence: Float = 0f,
    val sourceType: OcrSourceType = OcrSourceType.OFFLINE,
    val processedAt: Long = System.currentTimeMillis()
)

enum class OcrSourceType {
    OFFLINE,
    CLOUD
}
