package com.rickyhu.hushkeyboard.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.data.AppSettings
import com.rickyhu.hushkeyboard.data.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    settingsRepository: SettingsRepository,
) : ViewModel() {
    val settingsState =
        settingsRepository.settings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AppSettings(),
        )
}
