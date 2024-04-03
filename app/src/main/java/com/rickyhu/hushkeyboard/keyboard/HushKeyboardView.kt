package com.rickyhu.hushkeyboard.keyboard

import android.content.Context
import android.os.Build
import android.os.VibratorManager
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
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.ui.keyboard.ControlKeyButtonRow
import com.rickyhu.hushkeyboard.ui.keyboard.NotationKeyButtonsRow
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.utils.deleteText
import com.rickyhu.hushkeyboard.utils.inputText
import com.rickyhu.hushkeyboard.utils.maybeVibrate
import com.rickyhu.hushkeyboard.utils.toInputConnection
import splitties.systemservices.inputMethodManager

class HushKeyboardView(context: Context) : AbstractComposeView(context) {

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    override fun Content() {
        val viewModel = (context as HushIMEService).viewModel
        val state by viewModel.keyboardState.collectAsState(KeyboardState())

        HushKeyboardContent(state)
    }
}

@Composable
private fun HushKeyboardContent(state: KeyboardState) {
    val context = LocalContext.current

    val vibratorManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    } else {
        null
    }

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
            onTextInput = {
                context.toInputConnection().inputText(it)
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(keyConfigState),
            isDarkTheme = isDarkTheme,
            addSpaceAfterNotation = state.addSpaceAfterNotation,
            wideNotationOption = state.wideNotationOption,
            onTextInput = {
                context.toInputConnection().inputText(it)
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            }
        )

        ControlKeyButtonRow(
            turns = keyConfigState.turns,
            isDarkTheme = isDarkTheme,
            inputMethodButtonAction = {
                inputMethodManager.showInputMethodPicker()
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            rotateDirectionButtonAction = {
                keyConfigState = keyConfigState.copy(
                    isCounterClockwise = keyConfigState.isCounterClockwise
                )
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            turnDegreeButtonAction = {
                keyConfigState = when (keyConfigState.turns) {
                    Turns.Single -> keyConfigState.copy(turns = Turns.Double)
                    Turns.Double -> keyConfigState.copy(turns = Turns.Triple)
                    Turns.Triple -> keyConfigState.copy(turns = Turns.Single)
                }
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            wideTurnButtonAction = {
                keyConfigState = keyConfigState.copy(
                    isWideTurn = !keyConfigState.isWideTurn
                )
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            deleteButtonAction = {
                context.toInputConnection().deleteText()
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            newLineButtonAction = {
                context.toInputConnection().inputText("\n")
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            }
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
        )
    )
}
