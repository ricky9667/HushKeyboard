package com.rickyhu.hushkeyboard.ui.keyboard

import android.os.Build
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardState
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel
import splitties.systemservices.inputMethodManager


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HushKeyboard(viewModel: KeyboardViewModel = hiltViewModel()) {
    val state by viewModel.keyboardState.collectAsState(KeyboardState())

    HushKeyboardContent(state)
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun HushKeyboardContent(state: KeyboardState) {
    var keyConfigState by remember { mutableStateOf(CubeKey.Config()) }

    viewModel.shouldVibrate = settingsState.vibrateOnTap

    val isDarkTheme = state.themeOption.isDarkTheme(
        isSystemInDarkMode = isSystemInDarkTheme()
    )

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
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(keyConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = state.addSpaceAfterNotation,
            wideNotationOption = state.wideNotationOption,
            onTextInput = { text -> viewModel.inputText(context, text) }
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
            deleteButtonAction = { viewModel.deleteText(context) },
            newLineButtonAction = { viewModel.inputText(context, "\n") }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun HushKeyboardPreview() {
    HushKeyboard(viewModel = KeyboardViewModel())
}
