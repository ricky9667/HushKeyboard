package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Composable
fun KeyButton(key: CubeKey) {
    val context = LocalContext.current
    var inputKey by remember { mutableStateOf(key) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(48.dp)
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
                                inputKey = if (position.x < 0) {
                                    key.copy(isCounterClockwise = true)
                                } else {
                                    key.copy(turns = Turns.Double)
                                }
                            }

                            "Release" -> {
                                val service = context as HushIMEService
                                val text = "$inputKey "
                                service.currentInputConnection.commitText(text, text.length)
                            }
                        }
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = key.toString(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeyButtonPreview() {
    HushKeyboardTheme {
        KeyButton(CubeKey(Notation.R))
    }
}
