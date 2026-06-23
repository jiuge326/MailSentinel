package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.Attachment
import com.mailsentinel.domain.model.OcrResult
import com.mailsentinel.domain.repository.OcrRepository
import javax.inject.Inject

class ProcessOcrUseCase @Inject constructor(
    private val ocrRepository: OcrRepository
) {
    suspend operator fun invoke(
        attachment: Attachment,
        useCloud: Boolean = false,
        apiKey: String? = null
    ): Result<OcrResult> {
        return try {
            if (useCloud && apiKey != null) {
                ocrRepository.processWithCloud(attachment.localPath ?: "", apiKey)
            } else {
                ocrRepository.processImage(attachment.localPath ?: "")
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
