package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel

@Composable
fun HushKeyboard(viewModel: KeyboardViewModel) {
    val state by viewModel.keyboardState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val keyboardKeys = listOf(
            listOf(
                CubeKey(Notation.R, state.isCounterClockwise, state.turns, state.isWideTurn),
                CubeKey(Notation.U, state.isCounterClockwise, state.turns, state.isWideTurn),
                CubeKey(Notation.F, state.isCounterClockwise, state.turns, state.isWideTurn),
                CubeKey(Notation.L, state.isCounterClockwise, state.turns, state.isWideTurn),
                CubeKey(Notation.D, state.isCounterClockwise, state.turns, state.isWideTurn),
                CubeKey(Notation.B, state.isCounterClockwise, state.turns, state.isWideTurn)
            ),
            listOf(
                CubeKey(Notation.M, state.isCounterClockwise, state.turns),
                CubeKey(Notation.E, state.isCounterClockwise, state.turns),
                CubeKey(Notation.S, state.isCounterClockwise, state.turns),
                CubeKey(Notation.X, state.isCounterClockwise, state.turns),
                CubeKey(Notation.Y, state.isCounterClockwise, state.turns),
                CubeKey(Notation.Z, state.isCounterClockwise, state.turns)
            )
        )

        for (keysRow in keyboardKeys) {
            KeyButtonsRow(keysRow)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            KeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp)
                    .clickable(
                        onClick = { viewModel.switchCounterClockwise() }
                    ),
                text = "'"
            )
            KeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp)
                    .clickable(
                        onClick = { viewModel.switchTurns() }
                    ),
                text = state.turns.value.toString()
            )
            KeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp)
                    .clickable(
                        onClick = { viewModel.switchWideTurn() }
                    ),
                text = "w"
            )
        }
    }
}

@Composable
fun KeyButtonsRow(keys: List<CubeKey>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        keys.forEach { key ->
            NotationKeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp),
                key = key
            )
        }
    }
}
