package com.mailsentinel.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mailsentinel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.settings)) }
        )

        // OCR 设置
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.ocr_settings), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.offline_ocr))
                    Switch(
                        checked = uiState.offlineOcrEnabled,
                        onCheckedChange = { viewModel.toggleOfflineOcr(it) }
                    )
                }

                if (uiState.offlineOcrEnabled) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (uiState.modelDownloaded) "✓ ${stringResource(R.string.model_downloaded)}"
                               else stringResource(R.string.model_not_downloaded),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (uiState.modelDownloaded) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.error
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.cloud_ocr))
                    Switch(
                        checked = uiState.cloudOcrEnabled,
                        onCheckedChange = { viewModel.toggleCloudOcr(it) }
                    )
                }

                if (uiState.cloudOcrEnabled) {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.apiKey,
                        onValueChange = { viewModel.updateApiKey(it) },
                        label = { Text(stringResource(R.string.api_key)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.ai_model), style = MaterialTheme.typography.bodyMedium)

                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(onClick = { expanded = true }) {
                            Text(uiState.aiModel)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("GPT-4o") },
                                onClick = { viewModel.selectAiModel("gpt-4o"); expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("GPT-4o-mini") },
                                onClick = { viewModel.selectAiModel("gpt-4o-mini"); expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Claude-3.5") },
                                onClick = { viewModel.selectAiModel("claude-3.5"); expanded = false }
                            )
                        }
                    }
                }
            }
        }

        // 保活设置
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.keep_alive), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                val levels = listOf(
                    stringResource(R.string.keep_alive_normal),
                    stringResource(R.string.keep_alive_medium),
                    stringResource(R.string.keep_alive_aggressive)
                )

                levels.forEachIndexed { index, label ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = uiState.keepAliveLevel == index,
                            onClick = { viewModel.setKeepAliveLevel(index) }
                        )
                        Text(label)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.openAutoStartSettings() }) {
                    Text(stringResource(R.string.auto_start_guide))
                }
                OutlinedButton(onClick = { viewModel.requestBatteryOptimization() }) {
                    Text(stringResource(R.string.battery_optimization_guide))
                }
            }
        }
    }
}
