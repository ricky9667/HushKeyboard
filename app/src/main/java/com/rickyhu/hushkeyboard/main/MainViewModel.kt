package com.rickyhu.hushkeyboard.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    settingsRepository: SettingsRepository
) : ViewModel() {

    val settingsState = settingsRepository.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppSettings()
    )
}
