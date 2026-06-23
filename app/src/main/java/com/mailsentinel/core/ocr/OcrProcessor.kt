package com.mailsentinel.core.ocr

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions
import com.google.mlkit.vision.text.TextRecognizer
import com.mailsentinel.domain.model.OcrResult
import com.mailsentinel.domain.model.OcrSourceType
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class OcrProcessor(private val context: Context) {

    private val chineseRecognizer: TextRecognizer by lazy {
        TextRecognition.getClient(
            ChineseTextRecognizerOptions.Builder().build()
        )
    }

    suspend fun processImage(bitmap: Bitmap): OcrResult = try {
        suspendCancellableCoroutine { continuation ->
            val image = com.google.mlkit.vision.common.InputImage.fromBitmap(bitmap, 0)
            chineseRecognizer.process(image)
                .addOnSuccessListener { text ->
                    continuation.resume(
                        OcrResult(
                            recognizedText = text.text,
                            language = "zh",
                            confidence = 0.85f,
                            sourceType = OcrSourceType.OFFLINE
                        )
                    )
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
        }
    } catch (e: Exception) {
        OcrResult(
            recognizedText = "[OCR 识别失败: ${e.message}]",
            language = "zh",
            confidence = 0f,
            sourceType = OcrSourceType.OFFLINE
        )
    }

    suspend fun processImageUri(uri: Uri): OcrResult = try {
        val image = com.google.mlkit.vision.common.InputImage.fromFilePath(context, uri)
        suspendCancellableCoroutine { continuation ->
            chineseRecognizer.process(image)
                .addOnSuccessListener { text ->
                    continuation.resume(
                        OcrResult(
                            recognizedText = text.text,
                            language = "zh",
                            confidence = 0.85f,
                            sourceType = OcrSourceType.OFFLINE
                        )
                    )
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
        }
    } catch (e: Exception) {
        OcrResult(
            recognizedText = "[OCR 识别失败: ${e.message}]",
            language = "zh",
            confidence = 0f,
            sourceType = OcrSourceType.OFFLINE
        )
    }

    fun isModelDownloaded(): Boolean {
        return true
    }

    fun release() {
        try {
            chineseRecognizer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
