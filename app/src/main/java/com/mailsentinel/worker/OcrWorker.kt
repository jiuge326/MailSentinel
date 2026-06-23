package com.mailsentinel.worker

import android.content.Context
import androidx.work.*
import com.mailsentinel.domain.repository.OcrRepository
import com.mailsentinel.core.database.dao.AttachmentDao
import com.mailsentinel.core.utils.EncryptedPrefs
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OcrWorker @Inject constructor(
    @ApplicationContext private val context: Context,
    workerParams: WorkerParameters,
    private val ocrRepository: OcrRepository,
    private val attachmentDao: AttachmentDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val pendingImages = attachmentDao.getPendingOcrImages()

            for (attachment in pendingImages) {
                if (attachment.localPath != null) {
                    val apiKey = EncryptedPrefs.getApiKey(context, "cloud_ai_key")

                    val result = if (apiKey != null) {
                        ocrRepository.processWithCloud(
                            imagePath = attachment.localPath,
                            apiKey = apiKey
                        )
                    } else {
                        ocrRepository.processImage(
                            imagePath = attachment.localPath
                        )
                    }

                    if (result.isSuccess) {
                        val ocrResult = result.getOrNull()
                        if (ocrResult != null) {
                            attachmentDao.updateOcrResult(attachment.id, ocrResult.recognizedText)
                            ocrRepository.saveResult(ocrResult)
                        }
                    }
                }
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "ocr_process"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<OcrWorker>(1, java.util.concurrent.TimeUnit.HOURS)
                .setConstraints(Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request)
        }
    }
}
