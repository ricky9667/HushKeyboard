package com.rickyhu.hushkeyboard.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HushKeyboard(viewModel: KeyboardViewModel) {
    val state by viewModel.keyboardState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        val keyboardKeys = listOf(
            listOf(
                CubeKey(
                    Notation.R,
                    state.isCounterClockwise,
                    state.turns,
                    state.isWideTurn
                ),
                CubeKey(
                    Notation.U,
                    state.isCounterClockwise,
                    state.turns,
                    state.isWideTurn
                ),
                CubeKey(
                    Notation.F,
                    state.isCounterClockwise,
                    state.turns,
                    state.isWideTurn
                ),
                CubeKey(
                    Notation.L,
                    state.isCounterClockwise,
                    state.turns,
                    state.isWideTurn
                ),
                CubeKey(
                    Notation.D,
                    state.isCounterClockwise,
                    state.turns,
                    state.isWideTurn
                ),
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
            NotationKeyButtonsRow(
                keys = keysRow,
                onTextInput = { text -> viewModel.inputText(context, text) }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val controlKeyModifier = Modifier
                .padding(4.dp)
                .size(48.dp)

            ControlKeyButton(
                modifier = controlKeyModifier,
                text = "'",
                onClick = { viewModel.switchCounterClockwise(context) }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                text = state.turns.value.toString(),
                onClick = { viewModel.switchTurns(context) }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                text = "w",
                onClick = { viewModel.switchWideTurn(context) }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                text = "⌫",
                onClick = { viewModel.deleteText(context) }
            )
        }
    }
}

@Composable
private fun NotationKeyButtonsRow(
    keys: List<CubeKey>,
    onTextInput: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        keys.forEach { key ->
            NotationKeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp),
                cubeKey = key,
                onTextInput = onTextInput
            )
        }
    }
}
