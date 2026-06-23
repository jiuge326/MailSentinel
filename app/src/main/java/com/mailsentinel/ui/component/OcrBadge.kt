package com.mailsentinel.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OcrBadge(
    sourceType: String,  // "offline" or "cloud"
    confidence: Float,
    modifier: Modifier = Modifier
) {
    val label: String
    val color: androidx.compose.ui.graphics.Color
    when (sourceType) {
        "offline" -> { label = "离线OCR"; color = MaterialTheme.colorScheme.primary }
        "cloud" -> { label = "云端AI"; color = MaterialTheme.colorScheme.tertiary }
        else -> { label = "OCR"; color = MaterialTheme.colorScheme.onSurfaceVariant }
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = color
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${(confidence * 100).toInt()}%",
                style = MaterialTheme.typography.labelSmall,
                color = color
            )
        }
    }
}
