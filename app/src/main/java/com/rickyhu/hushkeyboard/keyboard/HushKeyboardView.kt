package com.rickyhu.hushkeyboard.keyboard

import android.content.Context
import android.os.Build
import android.os.VibratorManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
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
import com.rickyhu.hushkeyboard.keyboard.ui.rows.ControlKeyButtonRow
import com.rickyhu.hushkeyboard.keyboard.ui.rows.NotationKeyButtonsRow
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.service.HushIMEService
import com.rickyhu.hushkeyboard.theme.DarkBackground
import com.rickyhu.hushkeyboard.theme.LightBackground
import com.rickyhu.hushkeyboard.utils.deleteText
import com.rickyhu.hushkeyboard.utils.inputNewline
import com.rickyhu.hushkeyboard.utils.inputText
import com.rickyhu.hushkeyboard.utils.maybeVibrate
import com.rickyhu.hushkeyboard.utils.smartDelete
import com.rickyhu.hushkeyboard.utils.toInputConnection
import splitties.systemservices.inputMethodManager

private const val TAG = "HushKeyboardView"

class HushKeyboardView(context: Context) : AbstractComposeView(context) {

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    override fun Content() {
        val viewModel = (context as HushIMEService).viewModel
        val state by viewModel.keyboardState.collectAsState(KeyboardState())

        HushKeyboardContent(state)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
fun HushKeyboardContent(state: KeyboardState) {
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
                Log.d(TAG, "Notation key tapped")
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
            smartDelete = state.smartDelete,
            inputMethodButtonAction = {
                Log.d(TAG, "Input method picker tapped")
                inputMethodManager.showInputMethodPicker()
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            rotateDirectionButtonAction = {
                Log.d(TAG, "Rotate direction button tapped")
                keyConfigState = keyConfigState.copy(
                    isCounterClockwise = !keyConfigState.isCounterClockwise
                )
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            turnDegreeButtonAction = {
                Log.d(TAG, "Turn degree button tapped")
                keyConfigState = when (keyConfigState.turns) {
                    Turns.Single -> keyConfigState.copy(turns = Turns.Double)
                    Turns.Double -> keyConfigState.copy(turns = Turns.Triple)
                    Turns.Triple -> keyConfigState.copy(turns = Turns.Single)
                }
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            wideTurnButtonAction = {
                Log.d(TAG, "Wide turn button tapped")
                keyConfigState = keyConfigState.copy(
                    isWideTurn = !keyConfigState.isWideTurn
                )
                if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
            },
            deleteButtonAction = if (state.smartDelete) {
                {
                    Log.d(TAG, "Delete button tapped")
                    context.toInputConnection().smartDelete()
                    if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
                }
            } else {
                {
                    Log.d(TAG, "Smart delete button tapped")
                    context.toInputConnection().deleteText()
                    if (state.vibrateOnTap) vibratorManager?.maybeVibrate()
                }
            },
            newLineButtonAction = {
                Log.d(TAG, "New line button tapped")
                context.toInputConnection().inputNewline()
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
            wideNotationOption = WideNotationOption.WideWithW,
            smartDelete = true,
            addSpaceAfterNotation = true,
            vibrateOnTap = true
        )
    )
}
