package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val key by rememberUpdatedState(cubeKey)
    var inputKey by remember { mutableStateOf(key) }

    LaunchedEffect(key) {
        inputKey = key
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        KeyButton(
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onTextInput("$key ") }
                )
                .draggable(
                    interactionSource = interactionSource,
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { x ->
                        offsetX += x
                        inputKey = key.copy(isCounterClockwise = offsetX < 0)
                    },
                    onDragStarted = { offsetX = 0f },
                    onDragStopped = { onTextInput("$inputKey ") }
                )
                .draggable(
                    interactionSource = interactionSource,
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { y ->
                        offsetY += y

                        // Notate as double turn when swiping up or down, but only
                        // if the key state is originally a single turn.
                        val turns = if (key.turns == Turns.Single) {
                            Turns.Double
                        } else {
                            key.turns
                        }

                        // Clockwise down, counter-clockwise up.
                        inputKey = key.copy(isCounterClockwise = offsetY < 0, turns = turns)
                    },
                    onDragStarted = { offsetY = 0f },
                    onDragStopped = { onTextInput("$inputKey ") }
                ),
            text = key.toString()
        )

        if (isPressed) {
            KeyTooltip(text = key.toString(), modifier = modifier)
        } else if (isDragged) {
            KeyTooltip(text = inputKey.toString(), modifier = modifier)
        }
    }
}

@Composable
private fun KeyTooltip(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.offset(y = (-32).dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotationKeyButtonPreview() {
    HushKeyboardTheme {
        NotationKeyButton(
            modifier = Modifier
                .padding(4.dp)
                .size(48.dp),
            cubeKey = CubeKey(notation = Notation.R)
        )
    }
}
