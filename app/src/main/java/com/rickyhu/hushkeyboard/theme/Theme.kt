package com.rickyhu.hushkeyboard.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle

// Seed color for DynamicMaterialTheme - using a neutral blue-gray
private val SeedColor = Color(0xFF5B7C99)

@Composable
fun HushKeyboardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            // Only set status bar for Activity contexts, not IME services
            val context = view.context
            if (context is Activity) {
                val colorScheme =
                    when {
                        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                            if (darkTheme) {
                                dynamicDarkColorScheme(context)
                            } else {
                                dynamicLightColorScheme(context)
                            }
                        }

                        else -> null
                    }

                if (colorScheme != null) {
                    WindowCompat
                        .getInsetsController(
                            context.window,
                            view,
                        ).isAppearanceLightStatusBars = darkTheme
                }
            }
        }
    }

    DynamicMaterialTheme(
        seedColor = SeedColor,
        isDark = darkTheme,
        animate = true,
        style = PaletteStyle.TonalSpot,
        typography = Typography,
        content = content,
    )
}
