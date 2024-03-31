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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardState
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HushKeyboard(viewModel: KeyboardViewModel = hiltViewModel()) {
    val state by viewModel.keyboardState.collectAsState()

    HushKeyboardContent(state)
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun HushKeyboardContent(state: KeyboardState) {
    val keyboardConfigState by remember { mutableStateOf(CubeKey.Config()) }

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
            keys = NotationKeyProvider.getFirstRowKeys(keyboardConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = settingsState.addSpaceAfterNotation,
            wideNotationOption = settingsState.wideNotationOption,
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(keyboardConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = settingsState.addSpaceAfterNotation,
            wideNotationOption = settingsState.wideNotationOption,
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        ControlKeyButtonRow(
            turns = state.turns,
            isDarkTheme = isDarkTheme,
            inputMethodButtonAction = { viewModel.selectInputMethod(context) },
            rotateDirectionButtonAction = { viewModel.switchRotateDirection(context) },
            turnDegreeButtonAction = { viewModel.switchTurns(context) },
            wideTurnButtonAction = { viewModel.switchWideTurn(context) },
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
