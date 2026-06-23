package com.mailsentinel.core.service

import android.content.Context
import android.content.Intent
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class KeepAliveLevel {
    NORMAL,    // 仅前台服务
    MEDIUM,    // 前台服务 + WorkManager 辅助
    AGGRESSIVE  // 前台服务 + 媒体会话 + 静音音频
}

class KeepAliveManager(private val context: Context) {

    fun startService(level: KeepAliveLevel = KeepAliveLevel.MEDIUM) {
        try {
            val intent = Intent(context, MailSentinelService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopService() {
        try {
            val intent = Intent(context, MailSentinelService::class.java)
            context.stopService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun isServiceRunning(): Boolean = withContext(Dispatchers.IO) {
        try {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
            manager.getRunningServices(Int.MAX_VALUE)
                .any { it.service.className == MailSentinelService::class.java.name }
        } catch (e: Exception) {
            false
        }
    }
}
