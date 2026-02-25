package com.rickyhu.hushkeyboard.data

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val keyboardColorOption: KeyboardColorOption = KeyboardColorOption.NeutralGray,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
    val smartDelete: Boolean = true,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
)

enum class ThemeOption { System, Light, Dark }

enum class KeyboardColorOption(
    val color: Color,
    val displayName: String,
) {
    NeutralGray(Color(0xFF6B6B6B), "Neutral Gray"),
    OceanBlue(Color(0xFF0077BE), "Ocean Blue"),
    CoralRed(Color(0xFFE53935), "Coral Red"),
    ForestGreen(Color(0xFF2E7D32), "Forest Green"),
    DeepPurple(Color(0xFF512DA8), "Deep Purple"),
    AmberOrange(Color(0xFFFF8F00), "Amber Orange"),
    TealBlue(Color(0xFF00695C), "Teal Blue"),
    SlateGray(Color(0xFF455A64), "Slate Gray"),
    ;

    override fun toString() = displayName
}

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
