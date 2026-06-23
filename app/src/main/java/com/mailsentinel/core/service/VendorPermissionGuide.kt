package com.mailsentinel.core.service

import com.mailsentinel.domain.model.ConnectionError

object VendorPermissionGuide {

    fun getPermissionSteps(): List<PermissionStep> {
        return listOf(
            PermissionStep(
                title = "开启自启动权限",
                description = "允许邮件哨兵在后台自动启动，确保实时接收邮件",
                action = { context -> VendorAdapter.openAutoStartSettings(context) }
            ),
            PermissionStep(
                title = "关闭电池优化",
                description = "将邮件哨兵设为不受电池优化影响，防止后台被杀死",
                action = { context -> VendorAdapter.requestIgnoreBatteryOptimization(context) }
            )
        )
    }
}

data class PermissionStep(
    val title: String,
    val description: String,
    val action: (android.content.Context) -> Unit
)
