package com.rickyhu.hushkeyboard.ui.keyboard

import android.os.Build
import android.view.inputmethod.InputConnection
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardState
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel
import splitties.systemservices.inputMethodManager

@Composable
fun HushKeyboard(
    viewModel: KeyboardViewModel
) {
    val state by viewModel.keyboardState.collectAsState(KeyboardState())

    HushKeyboardContent(state)
}

@Composable
private fun HushKeyboardContent(
    state: KeyboardState
) {
    val context = LocalContext.current
    val inputConnection = (context as HushIMEService).currentInputConnection

    var keyConfigState by remember { mutableStateOf(CubeKey.Config()) }

    val isDarkTheme = when (state.themeOption) {
        ThemeOption.System -> isSystemInDarkTheme()
        ThemeOption.Light -> false
        ThemeOption.Dark -> true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isDarkTheme) DarkBackground else LightBackground)
            .padding(vertical = 32.dp)
    ) {
        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getFirstRowKeys(keyConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = state.addSpaceAfterNotation,
            wideNotationOption = state.wideNotationOption,
            onTextInput = { inputConnection.inputText(it) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(keyConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = state.addSpaceAfterNotation,
            wideNotationOption = state.wideNotationOption,
            onTextInput = { inputConnection.inputText(it) }
        )

        ControlKeyButtonRow(
            turns = keyConfigState.turns,
            isDarkTheme = isDarkTheme,
            inputMethodButtonAction = {
                inputMethodManager.showInputMethodPicker()
            },
            rotateDirectionButtonAction = {
                keyConfigState = keyConfigState.copy(
                    isCounterClockwise = keyConfigState.isCounterClockwise
                )
            },
            turnDegreeButtonAction = {
                keyConfigState = when (keyConfigState.turns) {
                    Turns.Single -> keyConfigState.copy(turns = Turns.Double)
                    Turns.Double -> keyConfigState.copy(turns = Turns.Triple)
                    Turns.Triple -> keyConfigState.copy(turns = Turns.Single)
                }
            },
            wideTurnButtonAction = {
                keyConfigState = keyConfigState.copy(
                    isWideTurn = !keyConfigState.isWideTurn
                )
            },
            deleteButtonAction = { inputConnection.deleteText() },
            newLineButtonAction = { inputConnection.inputText("\n") }
        )
    }
}

fun InputConnection.inputText(text: String) {
    commitText(text, 1)
}

fun InputConnection.deleteText() {
    val selectedText = getSelectedText(0)

    if (selectedText.isNullOrEmpty()) {
        deleteSurroundingText(1, 0)
    } else {
        commitText("", 1)
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun HushKeyboardPreview() {
    HushKeyboardContent(
        state = KeyboardState(
            themeOption = ThemeOption.System,
            addSpaceAfterNotation = true,
            vibrateOnTap = true,
            wideNotationOption = WideNotationOption.WideWithW
        )
    )
}
