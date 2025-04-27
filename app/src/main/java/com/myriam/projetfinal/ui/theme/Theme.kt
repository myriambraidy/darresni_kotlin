package com.myriam.projetfinal.ui.theme

import androidx.compose.ui.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



private val LightColorScheme = lightColorScheme(
    primary = PrimaryColorGreen,
    secondary = SecondaryColorPurple,
    background = LightBackgroundColor,
    surface = LightSurfaceColor,
    onPrimary = Color.White,
    onSecondary = LightTextSecondaryColor,
    onBackground = LightTextPrimaryColor,
    onSurface = LightTextPrimaryColor,
    error = ErrorColor,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorGreen,
    secondary = SecondaryColorPurple,
    background = DarkBackgroundColor,
    surface = DarkSurfaceColor,
    onPrimary = Color.Black,
    onSecondary = DarkTextSecondaryColor,
    onBackground = DarkTextPrimaryColor,
    onSurface = DarkTextPrimaryColor,
    error = ErrorColor,
    onError = Color.White
)

@Composable
fun ProjetFinalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}