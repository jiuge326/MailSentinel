package com.mailsentinel.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        GlassColor,
                        GlassColor.copy(alpha = 0.05f)
                    )
                ),
                shape = RoundedCornerShape(cornerRadius.dp)
            )
            .let { mod ->
                try {
                    // Android 12+ 使用 RenderEffect 实现模糊
                    mod.blur(radius = 10.dp)
                } catch (_: Exception) {
                    mod
                }
            }
    ) {
        content()
    }
}
