package com.rickyhu.hushkeyboard.settings

import android.content.Context
import androidx.datastore.dataStore
import com.rickyhu.hushkeyboard.settings.options.ThemeOption
import com.rickyhu.hushkeyboard.settings.options.WideNotationOption
import kotlinx.serialization.Serializable

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

@Serializable
data class AppSettings(
    val themeOption: ThemeOption = ThemeOption.System,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW
)
