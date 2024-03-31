package com.rickyhu.hushkeyboard.settings

import com.rickyhu.hushkeyboard.settings.options.ThemeOption
import com.rickyhu.hushkeyboard.settings.options.WideNotationOption
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW
)
