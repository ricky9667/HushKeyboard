package com.rickyhu.hushkeyboard.keyboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rickyhu.hushkeyboard.data.SettingsRepository

class KeyboardViewModelFactory(
    private val settingsRepository: SettingsRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KeyboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KeyboardViewModel(settingsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
