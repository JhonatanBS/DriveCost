package com.example.drivecost.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Green500,
    onPrimary = White,

    secondary = Gray900,
    onSecondary = White,

    tertiary = Green800,
    onTertiary = White,

    background = Gray500,
    onBackground = Black,

    surface = White,
    onSurface = Black,

    outline = Gray700,

    error = Alert,
    onError = White
)

@Composable
fun DriveCostTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}