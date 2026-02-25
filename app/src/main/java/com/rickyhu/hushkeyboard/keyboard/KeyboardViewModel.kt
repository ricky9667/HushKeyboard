package com.rickyhu.hushkeyboard.keyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.data.KeyboardColorOption
import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KeyboardViewModel(
    settingsRepository: SettingsRepository,
) : ViewModel() {
    val keyboardState =
        settingsRepository.settings
            .map { settings ->
                KeyboardState(
                    themeOption = settings.themeOption,
                    keyboardColorOption = settings.keyboardColorOption,
                    wideNotationOption = settings.wideNotationOption,
                    smartDelete = settings.smartDelete,
                    addSpaceAfterNotation = settings.addSpaceAfterNotation,
                    vibrateOnTap = settings.vibrateOnTap,
                )
            }.stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                KeyboardState(),
            )
}

data class KeyboardState(
    val themeOption: ThemeOption = ThemeOption.System,
    val keyboardColorOption: KeyboardColorOption = KeyboardColorOption.NeutralGray,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
    val smartDelete: Boolean = true,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
)
