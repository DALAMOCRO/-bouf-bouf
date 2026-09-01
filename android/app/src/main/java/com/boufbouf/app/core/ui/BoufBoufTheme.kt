package com.boufbouf.app.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BoufBoufColors = darkColorScheme(
    primary = Color(0xFFFFB35C),
    secondary = Color(0xFFFFD7A8),
    surface = Color(0xFF1E1712),
    background = Color(0xFF1E1712),
    onPrimary = Color(0xFF38230C),
    onSurface = Color(0xFFFFF8F3),
)

@Composable
fun BoufBoufTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = BoufBoufColors, content = content)
}
