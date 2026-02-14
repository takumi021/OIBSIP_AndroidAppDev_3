package com.example.calculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF2D6A4F),
    onPrimary = Color.White,
    secondary = Color(0xFF6B8F71),
    onSecondary = Color.White,
    background = Color(0xFFF4F7F3),
    onBackground = Color(0xFF1D1F1E),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1D1F1E)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF95D5B2),
    onPrimary = Color(0xFF003921),
    secondary = Color(0xFFB7CCBA),
    onSecondary = Color(0xFF243329),
    background = Color(0xFF101412),
    onBackground = Color(0xFFE2E3DE),
    surface = Color(0xFF1B1F1D),
    onSurface = Color(0xFFE2E3DE)
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
