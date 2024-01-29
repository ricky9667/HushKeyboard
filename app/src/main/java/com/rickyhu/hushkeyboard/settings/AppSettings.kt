package com.rickyhu.hushkeyboard.settings

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System
)

enum class ThemeOption {
    System, Light, Dark;

    fun isDarkTheme(isSystemInDarkMode: Boolean) = when (this) {
        System -> isSystemInDarkMode
        Light -> false
        Dark -> true
    }
}
