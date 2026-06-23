package com.mailsentinel.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mailsentinel.R
import com.mailsentinel.domain.model.MailMessage
import com.mailsentinel.ui.theme.GlassCard
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MailDetailScreen(
    message: MailMessage,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(message.subject ?: stringResource(R.string.no_subject)) }
        )

        // 发件人信息
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = message.fromName ?: message.fromAddress,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message.fromAddress,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                Text(
                    text = dateFormat.format(message.receivedDate),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 邮件正文
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = message.bodyText ?: message.previewText ?: "[无正文内容]",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // 附件提示
        if (message.hasAttachments) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.attachments),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
