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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.dataStore
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HushKeyboard(viewModel: KeyboardViewModel) {
    val context = LocalContext.current

    val keyboardState by viewModel.keyboardState.collectAsState()
    val settingsState by context.dataStore.data.collectAsState(initial = AppSettings())

    viewModel.shouldVibrate = settingsState.vibrateOnTap

    val isDarkTheme = settingsState.themeOption.isDarkTheme(
        isSystemInDarkMode = isSystemInDarkTheme()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isDarkTheme) DarkBackground else LightBackground)
            .padding(vertical = 32.dp)
    ) {
        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getFirstRowKeys(
                keyboardState.isCounterClockwise,
                keyboardState.turns,
                keyboardState.isWideTurn
            ),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = settingsState.addSpaceAfterNotation,
            wideNotationOption = settingsState.wideNotationOption,
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(
                keyboardState.isCounterClockwise,
                keyboardState.turns
            ),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = settingsState.addSpaceAfterNotation,
            wideNotationOption = settingsState.wideNotationOption,
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        ControlKeyButtonRow(
            turns = keyboardState.turns,
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
