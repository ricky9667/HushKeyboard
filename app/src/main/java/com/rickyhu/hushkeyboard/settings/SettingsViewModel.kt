package com.rickyhu.hushkeyboard.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.data.AppSettings
import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val settingsState = settingsRepository.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppSettings()
    ).map { settings ->
        SettingsState(
            themeOption = settings.themeOption,
            addSpaceAfterNotation = settings.addSpaceAfterNotation,
            smartDelete = settings.smartDelete,
            vibrateOnTap = settings.vibrateOnTap,
            wideNotationOption = settings.wideNotationOption
        )
    }

    fun updateThemeOption(themeOption: ThemeOption) {
        viewModelScope.launch {
            settingsRepository.updateThemeOption(themeOption)
        }
    }

    fun updateWideNotationOption(wideNotationOption: WideNotationOption) {
        viewModelScope.launch {
            settingsRepository.updateWideNotationOption(wideNotationOption)
        }
    }

    fun updateSmartDelete(smartDelete: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateSmartDelete(smartDelete)
        }
    }

    fun updateAddSpaceBetweenNotation(addSpaceBetweenNotation: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAddSpaceBetweenNotation(addSpaceBetweenNotation)
        }
    }

    fun updateVibrateOnTap(vibrateOnTap: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateVibrateOnTap(vibrateOnTap)
        }
    }
}

data class SettingsState(
    val themeOption: ThemeOption = ThemeOption.System,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW,
    val smartDelete: Boolean = true,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true
)
