package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme
import kotlin.math.abs

@Composable
fun NotationKeyButton(
    modifier: Modifier = Modifier,
    key: CubeKey
) {
    val context = LocalContext.current
    var inputKey by remember { mutableStateOf(key) }

    KeyButton(
        modifier = modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val position = event.changes.first().position

                        when (event.type.toString()) {
                            "Press" -> {
                                inputKey = key
                            }

                            "Move" -> {
                                if (abs(position.x) < abs(position.y)) {
                                    // Notate as double turn when swiping up or down, but only
                                    // if the key state is originally a single turn.
                                    val turns = if (key.turns == Turns.Single) {
                                        Turns.Double
                                    } else {
                                        key.turns
                                    }

                                    // Clockwise down, counter-clockwise up.
                                    inputKey = key.copy(
                                        isCounterClockwise = position.y < 0,
                                        turns = turns
                                    )
                                } else {
                                    // Clockwise right, counter-clockwise left.
                                    inputKey = key.copy(isCounterClockwise = position.x < 0)
                                }
                            }

                            "Release" -> {
                                val service = context as HushIMEService
                                val text = "$inputKey "
                                service.currentInputConnection.commitText(text, text.length)
                                val c = service.currentInputConnection
                            }
                        }
                    }
                }
            },
        text = key.toString()
    )
}

@Preview(showBackground = true)
@Composable
fun NotationKeyButtonPreview() {
    HushKeyboardTheme {
        NotationKeyButton(
            modifier = Modifier.size(48.dp),
            key = CubeKey(
                notation = Notation.R
            )
        )
    }
}
