package com.mailsentinel.core.ocr

import android.content.Context
import android.net.Uri
import com.mailsentinel.domain.model.OcrResult
import java.io.File

class OcrManager(private val context: Context) {

    private val processor = OcrProcessor(context)
    private val cloudRecognizer = CloudAiRecognizer()

    suspend fun processImage(
        imagePath: String,
        useCloud: Boolean = false,
        apiKey: String? = null
    ): OcrResult {
        return try {
            if (useCloud && apiKey != null) {
                val base64 = File(imagePath).readBytes().let {
                    android.util.Base64.encodeToString(it, android.util.Base64.NO_WRAP)
                }
                cloudRecognizer.recognizeWithGpt4o(base64, apiKey)
            } else {
                val uri = Uri.fromFile(File(imagePath))
                processor.processImageUri(uri)
            }
        } catch (e: Exception) {
            OcrResult(
                recognizedText = "[OCR 处理失败: ${e.message}]",
                language = "zh",
                confidence = 0f,
                sourceType = if (useCloud) com.mailsentinel.domain.model.OcrSourceType.CLOUD
                else com.mailsentinel.domain.model.OcrSourceType.OFFLINE
            )
        }
    }

    fun isOfflineModelReady(): Boolean = try {
        processor.isModelDownloaded()
    } catch (e: Exception) {
        false
    }

    fun release() {
        try {
            processor.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
