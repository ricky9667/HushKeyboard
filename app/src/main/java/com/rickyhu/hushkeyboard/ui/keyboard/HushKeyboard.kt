package com.rickyhu.hushkeyboard.ui.keyboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.ui.keyboard.buttons.ControlKeyButton
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
        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getFirstRowKeys(
                state.isCounterClockwise,
                state.turns,
                state.isWideTurn
            ),
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(
                state.isCounterClockwise,
                state.turns
            ),
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val controlKeyModifier = Modifier
                .padding(4.dp)
                .size(48.dp)

            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::selectInputMethod,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_language),
                        contentDescription = "Language"
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchCounterClockwise,
                content = {
                    Text(
                        "'",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchTurns,
                content = {
                    Text(
                        state.turns.value.toString(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchWideTurn,
                content = {
                    Text(
                        "w",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = { viewModel.deleteText(context) },
                content = {
                    Text(
                        "⌫",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}
