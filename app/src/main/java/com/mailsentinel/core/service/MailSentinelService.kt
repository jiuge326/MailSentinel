package com.mailsentinel.core.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.mailsentinel.R
import kotlinx.coroutines.*

class MailSentinelService : Service() {

    companion object {
        const val CHANNEL_ID = "mailsentinel_channel"
        const val NOTIFICATION_ID = 10001
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createForegroundNotification("邮件哨兵", "正在监听新邮件...")
        startForeground(NOTIFICATION_ID, notification)
        startMailListening()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private fun startMailListening() {
        // 这里将启动 IMAP IDLE 监听
        // 实际实现会读取账户列表，为每个账户启动监听
    }

    private fun createNotificationChannel() {
        try {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "邮件监听服务",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "邮件实时推送服务"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createForegroundNotification(title: String, content: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()
    }
}
