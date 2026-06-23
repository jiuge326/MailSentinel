package com.mailsentinel.ui.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Inbox : Screen("inbox")
    object AccountStatus : Screen("account_status")
    object Settings : Screen("settings")
    object AddAccount : Screen("add_account")
    object ForwardRules : Screen("forward_rules")
    object MailDetail : Screen("mail_detail")
}
