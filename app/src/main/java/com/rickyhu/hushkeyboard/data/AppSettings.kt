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

enum class KeyboardColorOption {
    NeutralGray,
    OceanBlue,
    CoralRed,
    ForestGreen,
    DeepPurple,
    AmberOrange,
    TealBlue,
    SlateGray,
    ;

    val color: Color
        get() =
            when (this) {
                NeutralGray -> Color(0xFF6B6B6B)
                OceanBlue -> Color(0xFF0077BE)
                CoralRed -> Color(0xFFE53935)
                ForestGreen -> Color(0xFF2E7D32)
                DeepPurple -> Color(0xFF512DA8)
                AmberOrange -> Color(0xFFFF8F00)
                TealBlue -> Color(0xFF00695C)
                SlateGray -> Color(0xFF455A64)
            }

    override fun toString() =
        when (this) {
            NeutralGray -> "Neutral Gray"
            OceanBlue -> "Ocean Blue"
            CoralRed -> "Coral Red"
            ForestGreen -> "Forest Green"
            DeepPurple -> "Deep Purple"
            AmberOrange -> "Amber Orange"
            TealBlue -> "Teal Blue"
            SlateGray -> "Slate Gray"
        }
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
