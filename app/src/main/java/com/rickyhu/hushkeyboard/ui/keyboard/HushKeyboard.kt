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
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardState
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel
import splitties.systemservices.inputMethodManager

@Composable
fun HushKeyboard(viewModel: KeyboardViewModel = hiltViewModel()) {
    val state by viewModel.keyboardState.collectAsState(KeyboardState())

    HushKeyboardContent(
        state,
        onTextInput = viewModel::inputText,
        onTextDelete = viewModel::deleteText
    )
}

@Composable
private fun HushKeyboardContent(
    state: KeyboardState,
    onTextInput: (String) -> Unit,
    onTextDelete: () -> Unit
) {
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
            onTextInput = onTextInput
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(keyConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = state.addSpaceAfterNotation,
            wideNotationOption = state.wideNotationOption,
            onTextInput = onTextInput
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
            deleteButtonAction = onTextDelete,
            newLineButtonAction = { onTextInput("\n") }
        )
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
        ),
        onTextInput = {},
        onTextDelete = {}
    )
}
