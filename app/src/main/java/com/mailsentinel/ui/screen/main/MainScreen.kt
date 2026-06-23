package com.mailsentinel.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.mailsentinel.R
import com.mailsentinel.ui.screen.inbox.InboxScreen
import com.mailsentinel.ui.screen.settings.SettingsScreen
import com.mailsentinel.ui.screen.status.AccountStatusScreen

@Composable
fun MainScreen(
    navController: NavController
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Email, contentDescription = stringResource(R.string.inbox)) },
                    label = { Text(stringResource(R.string.inbox)) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Person, contentDescription = stringResource(R.string.account_status)) },
                    label = { Text(stringResource(R.string.account_status)) }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings)) },
                    label = { Text(stringResource(R.string.settings)) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> InboxScreen(
                    onNavigateToStatus = { selectedTab = 1 },
                    onNavigateToSettings = { selectedTab = 2 }
                )
                1 -> AccountStatusScreen(
                    onNavigateBack = { selectedTab = 0 },
                    onNavigateToAddAccount = { navController.navigate("add_account") }
                )
                2 -> SettingsScreen(
                    onNavigateBack = { selectedTab = 0 }
                )
            }
        }
    }
}
