package com.mailsentinel.core.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.mailsentinel.R
import com.mailsentinel.core.database.dao.AccountDao
import com.mailsentinel.core.network.ConnectionStateManager
import com.mailsentinel.core.network.ImapClient
import com.mailsentinel.core.parser.MimeParser
import com.mailsentinel.domain.repository.ForwardRepository
import com.mailsentinel.domain.model.ConnectionState
import com.mailsentinel.domain.model.ForwardRule
import com.mailsentinel.domain.model.MailMessage
import com.mailsentinel.domain.model.RegexTarget
import com.mailsentinel.domain.repository.RuleMatchResult
import com.mailsentinel.core.regex.RegexMatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger
import javax.mail.internet.MimeMessage
import javax.inject.Inject

@AndroidEntryPoint
class MailSentinelService : Service() {

    companion object {
        const val CHANNEL_ID = "mailsentinel_channel"
        const val CHANNEL_ID_MAIL = "mailsentinel_mail_channel"
        const val NOTIFICATION_ID_FOREGROUND = 10001
        private val notificationIdCounter = AtomicInteger(10002)
    }

    @Inject lateinit var accountDao: AccountDao
    @Inject lateinit var imapClient: ImapClient
    @Inject lateinit var mimeParser: MimeParser
    @Inject lateinit var connectionStateManager: ConnectionStateManager
    @Inject lateinit var forwardRepository: ForwardRepository

    // Service 中使用 IO 线程的独立 scope
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val listeningJobs = mutableMapOf<Long, Job>()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createForegroundNotification("邮件哨兵", "正在监听新邮件...")
        startForeground(NOTIFICATION_ID_FOREGROUND, notification)
        startMailListening()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        listeningJobs.values.forEach { it.cancel() }
        listeningJobs.clear()
        serviceScope.cancel()
    }

    /**
     * 启动所有活跃账户的 IMAP IDLE 监听
     */
    private fun startMailListening() {
        serviceScope.launch {
            while (isActive) {
                try {
                    val accounts = accountDao.getAll()

                    // 清理已不存在的账户的 Job
                    val activeIds = accounts.map { it.id }.toSet()
                    listeningJobs.keys.filter { it !in activeIds }.forEach { id ->
                        listeningJobs.remove(id)?.cancel()
                    }

                    for (account in accounts) {
                        if (!account.isActive) continue
                        if (listeningJobs[account.id]?.isActive == true) continue

                        listeningJobs[account.id] = launch {
                            listenAccount(account)
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "启动监听失败")
                }

                // 每60秒检查一次账户变化
                delay(60_000)
            }
        }
    }

    /**
     * 单个账户的 IMAP IDLE 监听循环（自动重连）
     */
    private suspend fun listenAccount(account: com.mailsentinel.core.database.entity.AccountEntity) {
        var retryDelay = 5L

        while (kotlin.coroutines.coroutineContext.isActive) {
            try {
                Timber.d("开始监听账户: ${account.emailAddress}")
                connectionStateManager.updateState(account.id, ConnectionState.CONNECTING)

                val password = account.password ?: ""
                imapClient.observeNewMessages(
                    com.mailsentinel.domain.model.Account(
                        id = account.id,
                        emailAddress = account.emailAddress,
                        displayName = account.displayName,
                        imapHost = account.imapHost,
                        imapPort = account.imapPort.toInt(),
                        smtpHost = account.smtpHost,
                        smtpPort = account.smtpPort.toInt(),
                        useSsl = true,
                        tokenType = account.tokenType ?: "password",
                        connectionState = ConnectionState.CONNECTED
                    ),
                    password
                ).collect { mimeMessage ->
                    handleNewMessage(mimeMessage, account)
                }
            } catch (e: Exception) {
                Timber.e(e, "账户 ${account.emailAddress} 监听中断")
                connectionStateManager.updateState(account.id, ConnectionState.ERROR)
            }

            delay(retryDelay * 1000L)
            retryDelay = minOf(retryDelay * 2, 300L)
        }
    }

    /**
     * 处理新收到的邮件：解析 → 匹配规则 → 发通知
     */
    private suspend fun handleNewMessage(
        mimeMessage: javax.mail.Message,
        account: com.mailsentinel.core.database.entity.AccountEntity
    ) {
        try {
            val mimeMsg = mimeMessage as? MimeMessage ?: return

            // 1. 解析邮件正文
            val parsed = mimeParser.parseMessage(mimeMsg)
            val parsedBody = parsed?.first ?: return
            val _attachments = parsed.second ?: emptyList()

            // 2. 构建 MailMessage 对象用于规则匹配
            val fromAddr = (mimeMsg.from?.firstOrNull() as? javax.mail.internet.InternetAddress)?.address ?: ""
            val message = MailMessage(
                accountId = account.id,
                folderId = 0,
                uid = 0,
                subject = mimeMsg.subject,
                fromAddress = fromAddr,
                toAddresses = emptyList(),
                receivedDate = mimeMsg.receivedDate?.time ?: System.currentTimeMillis(),
                previewText = parsedBody.plainText.take(200),
                bodyText = parsedBody.plainText
            )

            // 3. 应用正则规则匹配
            val matchResults = applyRulesToMessage(message)

            if (matchResults.isNotEmpty()) {
                val subject = message.subject ?: "(无主题)"
                val displayTexts = matchResults.joinToString("\n") { it.displayText }
                showMatchNotification(subject, displayTexts, matchResults)
                Timber.d("邮件「$subject」命中 ${matchResults.size} 条规则")
            } else {
                val subject = message.subject ?: "(无主题)"
                showNewMailNotification(subject, fromAddr)
            }
        } catch (e: Exception) {
            Timber.e(e, "处理新邮件失败")
        }
    }

    /**
     * 应用规则并返回带匹配文本的结果
     */
    private suspend fun applyRulesToMessage(message: MailMessage): List<RuleMatchResult> {
        val matcher = RegexMatcher()
        val rules = getActiveRulesForAccount(message.accountId)
        val results = mutableListOf<RuleMatchResult>()

        for (rule in rules) {
            val targetText = when (rule.regexTarget) {
                RegexTarget.SUBJECT -> message.subject ?: ""
                RegexTarget.BODY -> message.bodyText ?: message.previewText ?: ""
                RegexTarget.FROM -> message.fromAddress
                RegexTarget.ANY ->
                    "${message.subject ?: ""} ${message.bodyText ?: ""} ${message.fromAddress}"
            }

            if (rule.regexPattern != null) {
                val match = matcher.findFirstMatch(rule.regexPattern, targetText)
                if (match != null) {
                    results.add(
                        RuleMatchResult(
                            rule = rule,
                            matchedText = match.matchedText,
                            captureGroups = match.captureGroups
                        )
                    )
                }
            }
        }
        return results
    }

    /**
     * 获取账户的活跃规则列表
     */
    private suspend fun getActiveRulesForAccount(accountId: Long): List<ForwardRule> {
        return try {
            forwardRepository.getActiveRules(accountId)
        } catch (_: Exception) { emptyList() }
    }

    /**
     * 显示命中规则的通知 — 点击即复制匹配结果，无跳转
     */
    private fun showMatchNotification(subject: String, matchedText: String, results: List<RuleMatchResult>) {
        val notificationId = notificationIdCounter.getAndIncrement()
        val primaryText = results.first().displayText

        // 复制按钮 PendingIntent（ACTION=COPY_MATCHED_TEXT 的 Broadcast）
        val copyIntent = Intent(this, CopyActionReceiver::class.java).apply {
            action = CopyActionReceiver.ACTION_COPY_MATCHED_TEXT
            putExtra(CopyActionReceiver.EXTRA_COPY_TEXT, primaryText)
            putExtra(CopyActionReceiver.EXTRA_NOTIFICATION_ID, notificationId)
        }
        val copyPendingIntent = PendingIntent.getBroadcast(
            this, notificationId, copyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 通知主体点击也执行复制（用户核心需求：点击即复制，无跳转 Activity）
        val clickCopyIntent = Intent(this, CopyActionReceiver::class.java).apply {
            action = CopyActionReceiver.ACTION_COPY_MATCHED_TEXT
            putExtra(CopyActionReceiver.EXTRA_COPY_TEXT, primaryText)
            putExtra(CopyActionReceiver.EXTRA_NOTIFICATION_ID, notificationId)
        }
        val clickCopyPendingIntent = PendingIntent.getBroadcast(
            this, notificationId + 1, clickCopyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID_MAIL)
            .setContentTitle("🎯 邮件匹配成功")
            .setContentText("$subject | $primaryText")
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                buildString {
                    appendLine("主题: $subject")
                    appendLine()
                    appendLine("匹配结果:")
                    results.forEachIndexed { index, result ->
                        appendLine("  ${index + 1}. [${result.rule.name}] ${result.displayText}")
                    }
                }
            ))
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setContentIntent(clickCopyPendingIntent)
            .addAction(android.R.drawable.ic_menu_save, "复制结果", copyPendingIntent)
            .build()

        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, notification)
    }

    /**
     * 显示普通新邮件通知（未匹配到规则时）
     */
    private fun showNewMailNotification(subject: String, fromAddress: String) {
        val notificationId = notificationIdCounter.getAndIncrement()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID_MAIL)
            .setContentTitle("📧 新邮件")
            .setContentText("$subject - $fromAddress")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, notification)
    }

    private fun createNotificationChannels() {
        val manager = getSystemService(NotificationManager::class.java)

        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "邮件监听服务",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "邮件实时推送保活服务"
        }
        manager.createNotificationChannel(serviceChannel)

        val mailChannel = NotificationChannel(
            CHANNEL_ID_MAIL,
            "新邮件通知",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "新邮件到达及规则匹配结果"
            enableLights(true)
            enableVibration(true)
        }
        manager.createNotificationChannel(mailChannel)
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
