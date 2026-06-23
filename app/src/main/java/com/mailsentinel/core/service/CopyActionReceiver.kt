package com.mailsentinel.core.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.lang.ref.WeakReference

/**
 * 通知栏点击/按钮触发的复制操作
 * 点击通知后直接复制匹配文本到剪贴板，不跳转任何 Activity
 */
class CopyActionReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_COPY_MATCHED_TEXT = "com.mailsentinel.action.COPY_MATCHED_TEXT"
        const val EXTRA_COPY_TEXT = "copy_text"
        const val EXTRA_NOTIFICATION_ID = "notification_id"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_COPY_MATCHED_TEXT) return

        val textToCopy = intent.getStringExtra(EXTRA_COPY_TEXT) ?: return
        val notificationId = intent.getIntExtra(EXTRA_NOTIFICATION_ID, 0)

        // 复制到剪贴板
        val clipboard = context.getSystemService(android.content.ClipboardManager::class.java)
        val clip = android.content.ClipData.newPlainText("邮件哨兵-匹配结果", textToCopy)
        clipboard?.setPrimaryClip(clip)

        // 取消通知（已处理）
        val notificationManager = context.getSystemService(android.app.NotificationManager::class.java)
        if (notificationId > 0) {
            notificationManager.cancel(notificationId)
        }

        // Toast 提示（需要通过主线程显示）
        val weakContext = WeakReference(context.applicationContext)
        Handler(Looper.getMainLooper()).post {
            weakContext.get()?.let { ctx ->
                Toast.makeText(ctx, "已复制: $textToCopy", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
