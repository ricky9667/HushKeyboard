package com.rickyhu.hushkeyboard.settings

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.serialization.Serializable

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW
)

enum class ThemeOption {
    System, Light, Dark;

    fun isDarkTheme(isSystemInDarkMode: Boolean) = when (this) {
        System -> isSystemInDarkMode
        Light -> false
        Dark -> true
    }
}

enum class WideNotationOption {
    WideWithW, WideWithLowercase;

    override fun toString() = when (this) {
        WideWithW -> "Use w (Rw)"
        WideWithLowercase -> "Use lowercase (r)"
    }
}
