package com.mailsentinel.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.mailsentinel.ui.theme.GlassColor

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .shadow(4.dp, shape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GlassColor,
                        GlassColor.copy(alpha = 0.05f)
                    )
                ),
                shape = shape
            )
            .background(
                MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                shape = shape
            )
    ) {
        content()
    }
}
