package com.rickyhu.hushkeyboard.keyboard.ui.buttons

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.theme.DarkPrimary
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import com.rickyhu.hushkeyboard.theme.LightPrimary

@Composable
fun NotationKeyButton(
    modifier: Modifier = Modifier,
    cubeKey: CubeKey,
    isDarkTheme: Boolean,
    addSpaceAfterNotation: Boolean,
    wideNotationOption: WideNotationOption,
    onTextInput: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

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
                    onClick = {
                        onTextInput(key.asText(addSpaceAfterNotation, wideNotationOption))
                    }
                )
                .draggable(
                    interactionSource = interactionSource,
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { x ->
                        offsetX += x
                        inputKey = key.copy(
                            config = key.config.copy(isCounterClockwise = offsetX < 0)
                        )
                    },
                    onDragStarted = { offsetX = 0f },
                    onDragStopped = {
                        onTextInput(
                            inputKey.asText(addSpaceAfterNotation, wideNotationOption)
                        )
                    }
                )
                .draggable(
                    interactionSource = interactionSource,
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { y ->
                        offsetY += y

                        // Notate as double turn when swiping up or down, but only if the key state
                        // is originally a single turn.
                        val turns = if (key.config.turns == Turns.Single) {
                            Turns.Double
                        } else {
                            key.config.turns
                        }

                        // Clockwise down, counter-clockwise up.
                        inputKey = key.copy(
                            config = key.config.copy(
                                isCounterClockwise = offsetY < 0,
                                turns = turns
                            )
                        )
                    },
                    onDragStarted = { offsetY = 0f },
                    onDragStopped = {
                        onTextInput(inputKey.asText(addSpaceAfterNotation, wideNotationOption))
                    }
                ),
            buttonColor = if (isDarkTheme) DarkPrimary else LightPrimary,
            content = {
                Text(
                    text = key.asText(addSpaceAfterNotation, wideNotationOption),
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )

        when {
            isPressed -> KeyTooltip(
                text = key.asText(addSpaceAfterNotation, wideNotationOption),
                isDarkTheme = isDarkTheme,
                modifier = modifier
            )

            isDragged -> KeyTooltip(
                text = inputKey.asText(addSpaceAfterNotation, wideNotationOption),
                isDarkTheme = isDarkTheme,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun KeyTooltip(
    text: String,
    isDarkTheme: Boolean,
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
                .background(color = if (isDarkTheme) DarkPrimary else LightPrimary)
                .padding(4.dp)
        ) {
            Text(
                text = text,
                color = if (isDarkTheme) Color.White else Color.Black,
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
            cubeKey = CubeKey(notation = Notation.R),
            isDarkTheme = false,
            addSpaceAfterNotation = true,
            wideNotationOption = WideNotationOption.WideWithW
        )
    }
}
