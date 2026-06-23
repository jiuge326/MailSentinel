package com.mailsentinel.ui.screen.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mailsentinel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddAccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.add_account)) }
        )

        OutlinedTextField(
            value = uiState.displayName,
            onValueChange = { viewModel.updateField("displayName", it) },
            label = { Text("显示名称") },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.emailAddress,
            onValueChange = { viewModel.updateField("emailAddress", it) },
            label = { Text(stringResource(R.string.email_address)) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.updateField("password", it) },
            label = { Text("密码/授权码") },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        // QQ邮箱授权码提示
        if (uiState.imapHost.contains("qq.com")) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "QQ邮箱需要使用授权码而非密码",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE65100)
                    )
                    Text(
                        text = "请在 QQ邮箱 → 设置 → 账户 → POP3/IMAP/SMTP 中生成授权码",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFBF360C)
                    )
                }
            }
        }

        // 163邮箱授权码提示
        if (uiState.imapHost.contains("163.com")) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "163邮箱需要使用授权码而非密码",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE65100)
                    )
                    Text(
                        text = "请在 163邮箱 → 设置 → POP3/IMAP/SMTP 中开启并生成授权码",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFBF360C)
                    )
                }
            }
        }

        OutlinedTextField(
            value = uiState.imapHost,
            onValueChange = { viewModel.updateField("imapHost", it) },
            label = { Text(stringResource(R.string.imap_server)) },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.imapPort,
            onValueChange = { viewModel.updateField("imapPort", it) },
            label = { Text(stringResource(R.string.imap_port)) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.smtpHost,
            onValueChange = { viewModel.updateField("smtpHost", it) },
            label = { Text(stringResource(R.string.smtp_server)) },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.smtpPort,
            onValueChange = { viewModel.updateField("smtpPort", it) },
            label = { Text(stringResource(R.string.smtp_port)) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uiState.useSsl,
                onCheckedChange = { viewModel.updateField("useSsl", it.toString()) }
            )
            Text(stringResource(R.string.use_ssl))
        }

        // 常用邮箱快捷配置
        Text("常用邮箱配置", style = MaterialTheme.typography.titleSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = false,
                onClick = { viewModel.applyQuickConfig("gmail") },
                label = { Text("Gmail") }
            )
            FilterChip(
                selected = false,
                onClick = { viewModel.applyQuickConfig("outlook") },
                label = { Text("Outlook") }
            )
            FilterChip(
                selected = false,
                onClick = { viewModel.applyQuickConfig("qq") },
                label = { Text("QQ邮箱") }
            )
            FilterChip(
                selected = false,
                onClick = { viewModel.applyQuickConfig("163") },
                label = { Text("163邮箱") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = onNavigateBack) {
                Text(stringResource(R.string.cancel))
            }

            Button(
                onClick = { viewModel.testConnection() },
                enabled = !uiState.isTesting
            ) {
                Text(if (uiState.isTesting) "测试中..." else stringResource(R.string.test_connection))
            }

            Button(
                onClick = { viewModel.saveAccount() },
                enabled = !uiState.isSaving && uiState.connectionTested
            ) {
                Text(if (uiState.isSaving) stringResource(R.string.saving) else stringResource(R.string.save))
            }
        }

        // 连接测试结果 - 显示详细原因和解决方案
        if (uiState.testResult != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (uiState.connectionTested) 
                        Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    if (uiState.connectionTested) {
                        Text(
                            text = "✓ 连接成功",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF2E7D32)
                        )
                    } else {
                        Text(
                            text = "✗ 连接失败",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFFC62828)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "原因：${uiState.testResult}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFD32F2F)
                        )
                        if (uiState.errorSolution != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "解决方案：${uiState.errorSolution}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF5D4037)
                            )
                        }
                    }
                }
            }
        }
    }
}
