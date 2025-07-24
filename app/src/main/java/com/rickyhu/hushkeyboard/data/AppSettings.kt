package com.rickyhu.hushkeyboard.data

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
    val smartDelete: Boolean = true,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true
)

enum class ThemeOption { System, Light, Dark }

enum class WideNotationOption {
    WideWithW, WideWithLowercase;

    override fun toString() = when (this) {
        WideWithW -> "Use w (Rw)"
        WideWithLowercase -> "Use lowercase (r)"
    }
}
