package com.mailsentinel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mailsentinel.ui.screen.account.AddAccountScreen
import com.mailsentinel.ui.screen.main.MainScreen
import com.mailsentinel.ui.screen.rules.RulesScreen
import com.mailsentinel.ui.screen.settings.SettingsScreen
import com.mailsentinel.ui.screen.status.AccountStatusScreen

@Composable
fun MailNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }

        composable(Screen.AddAccount.route) {
            AddAccountScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.ForwardRules.route) {
            RulesScreen()
        }
    }
}
