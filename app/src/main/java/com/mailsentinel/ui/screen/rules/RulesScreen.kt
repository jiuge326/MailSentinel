package com.mailsentinel.ui.screen.rules

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mailsentinel.R
import com.mailsentinel.domain.model.ForwardRule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesScreen(
    viewModel: RulesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(stringResource(R.string.forward_rules)) },
            actions = {
                IconButton(onClick = { viewModel.showAddDialog() }) {
                    Text("+")
                }
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.rules) { rule ->
                RuleCard(rule = rule, onDelete = { viewModel.deleteRule(rule.id) })
            }
        }

        // 添加规则对话框
        if (uiState.showAddDialog) {
            AddRuleDialog(
                onDismiss = { viewModel.hideAddDialog() },
                onConfirm = { viewModel.addRule(it) }
            )
        }
    }
}

@Composable
fun RuleCard(rule: ForwardRule, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(rule.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            if (rule.regexPattern != null) {
                Text("正则: ${rule.regexPattern}", style = MaterialTheme.typography.bodySmall)
            }
            if (rule.jsScript != null) {
                Text("JS: ${rule.jsScript.take(50)}", style = MaterialTheme.typography.bodySmall)
            }
            Text("转发至: ${rule.targetAddress}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Switch(checked = rule.isActive, onCheckedChange = {})
                Text(if (rule.isActive) "启用" else "禁用")
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onDelete) {
                    Text(stringResource(R.string.delete))
                }
            }
        }
    }
}

@Composable
fun AddRuleDialog(
    onDismiss: () -> Unit,
    onConfirm: (ForwardRule) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var regex by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加规则") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text(stringResource(R.string.rule_name)) })
                OutlinedTextField(value = regex, onValueChange = { regex = it }, label = { Text(stringResource(R.string.regex_pattern)) })
                OutlinedTextField(value = target, onValueChange = { target = it }, label = { Text(stringResource(R.string.target_email)) })
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(ForwardRule(
                    accountId = 0,
                    name = name,
                    regexPattern = regex.ifBlank { null },
                    targetAddress = target
                ))
            }) {
                Text("确认")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}
