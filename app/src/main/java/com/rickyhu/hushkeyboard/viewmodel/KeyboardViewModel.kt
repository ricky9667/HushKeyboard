package com.rickyhu.hushkeyboard.viewmodel

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import splitties.systemservices.inputMethodManager
import javax.inject.Inject

@HiltViewModel
class KeyboardViewModel @Inject constructor(
    settingsRepository: SettingsRepository
) : ViewModel() {
    private val settingsState = settingsRepository.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppSettings()
    )

    var shouldVibrate: Boolean = true

    // TODO: should remove context from ViewModel
    fun switchRotateDirection(context: Context) {
        _keyboardState.update { state ->
            state.copy(isCounterClockwise = !state.isCounterClockwise)
        }

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    fun switchTurns(context: Context) {
        _keyboardState.update { state ->
            when (state.turns) {
                Turns.Single -> state.copy(turns = Turns.Double)
                Turns.Double -> state.copy(turns = Turns.Triple)
                Turns.Triple -> state.copy(turns = Turns.Single)
            }
        }

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    fun switchWideTurn(context: Context) {
        _keyboardState.update { state ->
            state.copy(isWideTurn = !state.isWideTurn)
        }

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    fun deleteText(context: Context) {
        val inputConnection = (context as HushIMEService).currentInputConnection
        val selectedText = inputConnection.getSelectedText(0)
        if (TextUtils.isEmpty(selectedText)) {
            inputConnection.deleteSurroundingText(1, 0)
        } else {
            inputConnection.commitText("", CURSOR_POSITION)
        }

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    fun inputText(context: Context, text: String) {
        val inputConnection = (context as HushIMEService).currentInputConnection
        inputConnection.commitText(text, CURSOR_POSITION)

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    fun selectInputMethod(context: Context) {
        inputMethodManager.showInputMethodPicker()

        if (!shouldVibrate) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    // TODO: should remove context from ViewModel
    @RequiresApi(Build.VERSION_CODES.S)
    private fun vibrate(context: Context) {
        val vibratorManager = context.getSystemService(
            Context.VIBRATOR_MANAGER_SERVICE
        ) as VibratorManager

        val effectId = VibrationEffect.Composition.PRIMITIVE_CLICK

        vibratorManager.vibrate(
            CombinedVibration.createParallel(
                VibrationEffect.startComposition()
                    .addPrimitive(effectId)
                    .compose()
            )
        )
    }

    companion object {
        private const val CURSOR_POSITION = 1
    }
}

data class KeyboardState(
    val keyboardConfig: CubeKey.Config
)
