package com.mailsentinel.ui.screen.status

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mailsentinel.R
import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.model.ConnectionState
import com.mailsentinel.domain.model.ConnectionError
import com.mailsentinel.ui.component.GlassCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountStatusScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddAccount: () -> Unit,
    viewModel: AccountStatusViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.account_status)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "刷新")
                    }
                    IconButton(onClick = onNavigateToAddAccount) {
                        Icon(Icons.Default.Add, contentDescription = "添加账户")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (uiState.isRefreshing) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (uiState.accounts.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.no_accounts))
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onNavigateToAddAccount) {
                            Text(stringResource(R.string.go_config))
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.accounts) { account ->
                        AccountStatusCard(
                            account = account,
                            error = uiState.errors[account.id],
                            onGoConfig = onNavigateToAddAccount,
                            onRetry = { viewModel.retryConnection(account.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AccountStatusCard(
    account: Account,
    error: ConnectionError?,
    onGoConfig: () -> Unit,
    onRetry: () -> Unit
) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = account.displayName,
                    style = MaterialTheme.typography.titleMedium
                )
                ConnectionStateBadge(state = account.connectionState)
            }

            Text(
                text = account.emailAddress,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (error != null || account.connectionState == ConnectionState.ERROR) {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = error?.message ?: account.lastError ?: "连接错误",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = error?.solution ?: "请检查账户配置",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (error?.actionLabel != null) {
                                Button(onClick = onGoConfig) {
                                    Text(error.actionLabel)
                                }
                            }
                            OutlinedButton(onClick = onRetry) {
                                Text(stringResource(R.string.retry))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConnectionStateBadge(state: ConnectionState) {
    val text: String
    val color: androidx.compose.ui.graphics.Color
    when (state) {
        ConnectionState.CONNECTED -> { text = stringResource(R.string.online); color = MaterialTheme.colorScheme.primary }
        ConnectionState.CONNECTING -> { text = stringResource(R.string.connecting); color = MaterialTheme.colorScheme.tertiary }
        ConnectionState.ERROR -> { text = stringResource(R.string.error); color = MaterialTheme.colorScheme.error }
        ConnectionState.DISCONNECTED -> { text = stringResource(R.string.offline); color = MaterialTheme.colorScheme.onSurfaceVariant }
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}
