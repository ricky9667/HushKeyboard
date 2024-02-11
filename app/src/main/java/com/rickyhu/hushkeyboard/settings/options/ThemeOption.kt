package com.rickyhu.hushkeyboard.settings.options

enum class ThemeOption {
    System, Light, Dark;

    fun isDarkTheme(isSystemInDarkMode: Boolean) = when (this) {
        System -> isSystemInDarkMode
        Light -> false
        Dark -> true
    }
}
