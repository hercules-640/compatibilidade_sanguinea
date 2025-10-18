package com.herculesestacio.compatibilidade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Cores
val RedPrimary = Color(0xFFB71C1C)
val RedLight   = Color(0xFFE57373)
val Surface    = Color(0xFFFFFFFF)
val TextMain   = Color(0xFF212121)
val ErrorRed   = Color(0xFFD32F2F)

private val LightScheme = lightColorScheme(
    primary = RedPrimary,
    onPrimary = Surface,
    background = Surface,
    onBackground = TextMain,
    surface = Surface,
    onSurface = TextMain,
    error = ErrorRed,
    onError = Surface
)

private val AppTypography = androidx.compose.material3.Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, // Roboto padrão
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightScheme,
        typography = AppTypography,
        content = content
    )
}
