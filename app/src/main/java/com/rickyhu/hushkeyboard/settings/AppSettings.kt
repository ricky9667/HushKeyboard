package com.rickyhu.hushkeyboard.settings

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.SYSTEM
)

enum class ThemeOption {
    SYSTEM, LIGHT, DARK;

    fun isDarkTheme(isSystemInDarkMode: Boolean) = when (this) {
        SYSTEM -> isSystemInDarkMode
        LIGHT -> false
        DARK -> true
    }
}
