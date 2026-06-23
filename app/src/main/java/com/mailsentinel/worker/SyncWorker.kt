package com.mailsentinel.worker

import android.content.Context
import androidx.work.*
import com.mailsentinel.domain.repository.MailRepository
import com.mailsentinel.domain.model.Account
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncWorker @Inject constructor(
    @ApplicationContext private val context: Context,
    workerParams: WorkerParameters,
    private val mailRepository: MailRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val accounts = mailRepository.getAllAccounts()
            if (accounts.isEmpty()) {
                return@withContext Result.success()  // 无账户，无需同步
            }

            for (account in accounts) {
                if (account.isActive) {
                    val syncResult = mailRepository.syncMessages(account.id)
                    if (syncResult.isFailure) {
                        // 单个账户失败不影响其他账户
                        continue
                    }
                }
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()  // 网络错误时自动重试
        }
    }

    companion object {
        const val WORK_NAME = "mail_sync"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<SyncWorker>(15, java.util.concurrent.TimeUnit.MINUTES)
                .setConstraints(Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
                )
                .setBackoffCriteria(BackoffPolicy.LINEAR, 30, java.util.concurrent.TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request)
        }
    }
}
