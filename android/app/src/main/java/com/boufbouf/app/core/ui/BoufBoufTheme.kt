package com.boufbouf.app.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ============================================================
// BOUF-BOUF — BRAND COLORS
// Source: Bouf-Bouf Charte Graphique & Guide de Marque V1.0
// ============================================================

val BoufBoufOrange = Color(0xFFFF6B35)
val BoufBoufAmber = Color(0xFFF7931E)
val BoufBoufCoral = Color(0xFFFF3B5C)
val BoufBoufCream = Color(0xFFFFF8F0)
val BoufBoufCharcoal = Color(0xFF1A1A1A)

// ============================================================
// DARK MODE — DEFAULT
// ============================================================

private val BoufBoufDarkColors = darkColorScheme(
    primary = BoufBoufOrange,
    secondary = BoufBoufAmber,
    background = BoufBoufCharcoal,
    surface = BoufBoufCharcoal,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = BoufBoufCream,
    onSurface = BoufBoufCream,
)

// ============================================================
// LIGHT MODE
// ============================================================

private val BoufBoufLightColors = lightColorScheme(
    primary = BoufBoufOrange,
    secondary = BoufBoufAmber,
    background = BoufBoufCream,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = BoufBoufCharcoal,
    onSurface = BoufBoufCharcoal,
)

// ============================================================
// THEME
// ============================================================

@Composable
fun BoufBoufTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        BoufBoufDarkColors
    } else {
        BoufBoufLightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}
