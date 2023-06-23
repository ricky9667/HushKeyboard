package com.rickyhu.hushkeyboard.viewmodel

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KeyboardViewModel : ViewModel() {
    private val _keyboardState = MutableStateFlow(KeyboardState())
    val keyboardState = _keyboardState.asStateFlow()

    fun switchCounterClockwise(context: Context) {
        _keyboardState.update { state ->
            state.copy(isCounterClockwise = !state.isCounterClockwise)
        }
    }

    fun switchTurns(context: Context) {
        _keyboardState.update { state ->
            when (state.turns) {
                Turns.Single -> state.copy(turns = Turns.Double)
                Turns.Double -> state.copy(turns = Turns.Triple)
                Turns.Triple -> state.copy(turns = Turns.Single)
            }
        }
    }

    fun switchWideTurn(context: Context) {
        _keyboardState.update { state ->
            state.copy(isWideTurn = !state.isWideTurn)
        }
    }

    fun deleteText(context: Context) {
        val inputConnection = (context as HushIMEService).currentInputConnection
        val selectedText = inputConnection.getSelectedText(0)
        if (TextUtils.isEmpty(selectedText)) {
            inputConnection.deleteSurroundingText(1, 0)
        } else {
            inputConnection.commitText("", 1)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

    fun inputText(context: Context, text: String) {
        val inputConnection = (context as HushIMEService).currentInputConnection
        inputConnection.commitText(text, text.length)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrate(context)
        }
    }

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
}

data class KeyboardState(
    val isCounterClockwise: Boolean = false,
    val turns: Turns = Turns.Single,
    val isWideTurn: Boolean = false
)
