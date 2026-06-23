package com.mailsentinel.data.repository

import com.mailsentinel.core.database.dao.OcrResultDao
import com.mailsentinel.core.ocr.CloudAiRecognizer
import com.mailsentinel.core.ocr.OcrManager
import com.mailsentinel.data.mapper.*
import com.mailsentinel.domain.model.*
import com.mailsentinel.domain.repository.OcrRepository
import javax.inject.Inject

class OcrRepositoryImpl @Inject constructor(
    private val ocrManager: OcrManager,
    private val ocrResultDao: OcrResultDao
) : OcrRepository {

    override suspend fun processImage(imagePath: String, language: String): Result<OcrResult> = try {
        val result = ocrManager.processImage(imagePath, useCloud = false)
        if (result.recognizedText.startsWith("[OCR")) {
            Result.failure(Exception(result.recognizedText))
        } else {
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun processWithCloud(imagePath: String, apiKey: String, model: String): Result<OcrResult> = try {
        val result = ocrManager.processImage(imagePath, useCloud = true, apiKey = apiKey)
        if (result.recognizedText.startsWith("[云端")) {
            Result.failure(Exception(result.recognizedText))
        } else {
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun saveResult(result: OcrResult) = try {
        val entity = com.mailsentinel.core.database.entity.OcrResultEntity(
            attachmentId = result.attachmentId,
            messageId = result.messageId,
            recognizedText = result.recognizedText,
            language = result.language,
            confidence = result.confidence,
            sourceType = result.sourceType.name.lowercase(),
            processedAt = result.processedAt
        )
        ocrResultDao.insert(entity)
    } catch (_: Exception) {}

    override suspend fun getResultByAttachment(attachmentId: Long): OcrResult? = try {
        ocrResultDao.getByAttachment(attachmentId)?.let { entity ->
            OcrResult(
                id = entity.id,
                attachmentId = entity.attachmentId,
                messageId = entity.messageId,
                recognizedText = entity.recognizedText,
                language = entity.language,
                confidence = entity.confidence,
                sourceType = OcrSourceType.valueOf(entity.sourceType.uppercase()),
                processedAt = entity.processedAt
            )
        }
    } catch (_: Exception) { null }

    override fun isOfflineModelDownloaded(): Boolean = ocrManager.isOfflineModelReady()

    override suspend fun downloadOfflineModel(): Result<Unit> = Result.success(Unit)  // ML Kit 自动管理模型下载
}
