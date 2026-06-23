package com.mailsentinel.domain.repository

import com.mailsentinel.domain.model.*

interface OcrRepository {
    suspend fun processImage(imagePath: String, language: String = "zh"): Result<OcrResult>
    suspend fun processWithCloud(imagePath: String, apiKey: String, model: String = "gpt-4o"): Result<OcrResult>
    suspend fun saveResult(result: OcrResult)
    suspend fun getResultByAttachment(attachmentId: Long): OcrResult?
    fun isOfflineModelDownloaded(): Boolean
    suspend fun downloadOfflineModel(): Result<Unit>
}
