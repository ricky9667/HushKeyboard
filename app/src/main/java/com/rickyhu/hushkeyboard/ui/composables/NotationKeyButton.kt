package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Composable
fun NotationKeyButton(
    modifier: Modifier = Modifier,
    cubeKey: CubeKey,
    onTextInput: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val key by rememberUpdatedState(cubeKey)
    var inputKey by remember { mutableStateOf(key) }

    KeyButton(
        modifier = modifier
            .clickable(interactionSource = interactionSource, indication = null) {
                inputKey = key
                onTextInput("$inputKey ")
            }
            .draggable(
                interactionSource = interactionSource,
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { x ->
                    inputKey = key.copy(isCounterClockwise = x < 0)
                },
                onDragStopped = { x ->
                    onTextInput("$inputKey ")
                }
            )
            .draggable(
                interactionSource = interactionSource,
                orientation = Orientation.Vertical,
                state = rememberDraggableState { y ->
                    // Notate as double turn when swiping up or down, but only
                    // if the key state is originally a single turn.
                    val turns = if (key.turns == Turns.Single) {
                        Turns.Double
                    } else {
                        key.turns
                    }

                    // Clockwise down, counter-clockwise up.
                    inputKey = key.copy(isCounterClockwise = y < 0, turns = turns)
                },
                onDragStopped = { y ->
                    onTextInput("$inputKey ")
                }
            ),
        text = key.toString()
    )
}

@Preview(showBackground = true)
@Composable
fun NotationKeyButtonPreview() {
    HushKeyboardTheme {
        NotationKeyButton(
            modifier = Modifier.size(48.dp),
            cubeKey = CubeKey(notation = Notation.R)
        )
    }
}
