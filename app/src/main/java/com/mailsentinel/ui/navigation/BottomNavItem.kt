package com.mailsentinel.ui.navigation

import androidx.annotation.StringRes

data class BottomNavItem(
    val screen: Screen,
    @StringRes val titleRes: Int,
    val icon: Int  // 图标资源 ID，暂时用占位
)
