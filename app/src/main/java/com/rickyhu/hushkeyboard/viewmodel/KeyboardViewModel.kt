package com.rickyhu.hushkeyboard.viewmodel

import androidx.lifecycle.ViewModel
import com.rickyhu.hushkeyboard.model.Turns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KeyboardViewModel : ViewModel() {
    private val _keyboardState = MutableStateFlow(KeyboardState())
    val keyboardState = _keyboardState.asStateFlow()

    fun switchCounterClockwise() {
        _keyboardState.update { state ->
            state.copy(isCounterClockwise = !state.isCounterClockwise)
        }
    }

    fun switchTurns() {
        _keyboardState.update { state ->
            when (state.turns) {
                Turns.Single -> state.copy(turns = Turns.Double)
                Turns.Double -> state.copy(turns = Turns.Triple)
                Turns.Triple -> state.copy(turns = Turns.Single)
            }
        }
    }

    fun switchWideTurn() {
        _keyboardState.update { state ->
            state.copy(isWideTurn = !state.isWideTurn)
        }
    }
}

data class KeyboardState(
    val isCounterClockwise: Boolean = false,
    val turns: Turns = Turns.Single,
    val isWideTurn: Boolean = false
)
