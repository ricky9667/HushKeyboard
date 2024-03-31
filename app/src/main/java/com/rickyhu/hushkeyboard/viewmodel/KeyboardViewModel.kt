package com.rickyhu.hushkeyboard.viewmodel

import android.text.TextUtils
import android.view.inputmethod.InputConnection
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.data.AppSettings
import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class KeyboardViewModel @Inject constructor(
    settingsRepository: SettingsRepository,
    private val inputConnection: InputConnection
) : ViewModel() {

    val keyboardState = settingsRepository.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppSettings()
    ).map { settings ->
        KeyboardState(
            themeOption = settings.themeOption,
            addSpaceAfterNotation = settings.addSpaceAfterNotation,
            vibrateOnTap = settings.vibrateOnTap,
            wideNotationOption = settings.wideNotationOption
        )
    }

    fun inputText(text: String) {
        inputConnection.commitText(text, CURSOR_POSITION)
    }

    fun deleteText() {
        val selectedText = inputConnection.getSelectedText(0)

        if (TextUtils.isEmpty(selectedText)) {
            inputConnection.deleteSurroundingText(1, 0)
        } else {
            inputConnection.commitText("", CURSOR_POSITION)
        }
    }

    companion object {
        private const val CURSOR_POSITION = 1
    }
}

data class KeyboardState(
    val themeOption: ThemeOption = ThemeOption.System,
    val addSpaceAfterNotation: Boolean = true,
    val vibrateOnTap: Boolean = true,
    val wideNotationOption: WideNotationOption = WideNotationOption.WideWithW
)
