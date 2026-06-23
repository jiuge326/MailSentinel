package com.mailsentinel.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.mailsentinel.R
import com.mailsentinel.core.service.CopyActionReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 匹配结果通知助手
 * 当邮件内容匹配规则时，推送高优先级通知
 */
@Singleton
class MatchNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        const val MATCH_CHANNEL_ID = "mailsentinel_match_channel"
        const val MATCH_CHANNEL_NAME = "邮件匹配通知"
        const val MATCH_NOTIFICATION_ID_START = 20000
    }

    private var notificationIdCounter = MATCH_NOTIFICATION_ID_START

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            MATCH_CHANNEL_ID,
            MATCH_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH  // 高优先级，弹出通知
        ).apply {
            description = "邮件内容匹配规则时的通知"
            setShowBadge(true)
        }
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    /**
     * 显示匹配结果通知
     * @param ruleName 规则名称
     * @param matchedText 匹配到的内容
     * @param accountName 账户名称（用于通知标题）
     */
    fun showMatchNotification(
        ruleName: String,
        matchedText: String,
        accountName: String = ""
    ): Int {
        val notificationId = notificationIdCounter++
        val title = if (accountName.isNotBlank()) {
            "MailSentinel - $accountName"
        } else {
            "MailSentinel - 内容匹配"
        }

        // 创建"复制内容"动作的 PendingIntent（使用已有的 CopyActionReceiver）
        val copyIntent = Intent(context, CopyActionReceiver::class.java).apply {
            action = CopyActionReceiver.ACTION_COPY_MATCHED_TEXT
            putExtra(CopyActionReceiver.EXTRA_COPY_TEXT, matchedText)
            putExtra(CopyActionReceiver.EXTRA_NOTIFICATION_ID, notificationId)
        }
        val copyPendingIntent = android.app.PendingIntent.getBroadcast(
            context,
            notificationId,
            copyIntent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_MUTABLE
        )

        // 创建通知
        val notification = NotificationCompat.Builder(context, MATCH_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText("规则「$ruleName」匹配到内容")
            .setStyle(NotificationCompat.BigTextStyle().bigText(matchedText))
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_notification,
                "复制内容",
                copyPendingIntent
            )
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, notification)

        return notificationId
    }
}
