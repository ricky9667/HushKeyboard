package com.rickyhu.hushkeyboard.data

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
)

enum class ThemeOption { System, Light, Dark }

enum class WideNotationOption {
    WideWithW,
    WideWithLowercase,
    ;

    override fun toString() =
        when (this) {
            WideWithW -> "Use w (Rw)"
            WideWithLowercase -> "Use lowercase (r)"
        }
}
