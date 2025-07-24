package com.rickyhu.hushkeyboard.keyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.data.AppSettings
import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KeyboardViewModel @Inject constructor(
    settingsRepository: SettingsRepository
) : ViewModel() {

    val keyboardState = settingsRepository.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppSettings()
    ).map { settings ->
        KeyboardState(
            themeOption = settings.themeOption,
            wideNotationOption = settings.wideNotationOption,
            smartDelete = settings.smartDelete,
            addSpaceAfterNotation = settings.addSpaceAfterNotation,
            vibrateOnTap = settings.vibrateOnTap
        )
    }
}

data class KeyboardState(
    val themeOption: ThemeOption = ThemeOption.System,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
    val smartDelete: Boolean = true,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true
)
