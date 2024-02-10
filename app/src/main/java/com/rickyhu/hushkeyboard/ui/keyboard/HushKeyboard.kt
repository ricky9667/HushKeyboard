package com.rickyhu.hushkeyboard.ui.keyboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.model.NotationKeyProvider
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.AppSettingsSerializer
import com.rickyhu.hushkeyboard.ui.keyboard.buttons.ControlKeyButton
import com.rickyhu.hushkeyboard.ui.theme.DarkBackground
import com.rickyhu.hushkeyboard.ui.theme.LightBackground
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HushKeyboard(viewModel: KeyboardViewModel) {
    val context = LocalContext.current
    val keyboardState by viewModel.keyboardState.collectAsState()
    val settingsState by context.dataStore.data.collectAsState(initial = AppSettings())

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
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        NotationKeyButtonsRow(
            keys = NotationKeyProvider.getSecondRowKeys(
                keyboardState.isCounterClockwise,
                keyboardState.turns
            ),
            isDarkTheme = isDarkTheme,
            onTextInput = { text -> viewModel.inputText(context, text) }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val controlKeyModifier = Modifier
                .padding(4.dp)
                .size(48.dp)

            // TODO: unify button colors
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::selectInputMethod,
                isDarkTheme = isDarkTheme,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_language),
                        tint = if (isDarkTheme) Color.White else Color.Black,
                        contentDescription = "Language"
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchCounterClockwise,
                isDarkTheme = isDarkTheme,
                content = {
                    Text(
                        "'",
                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchTurns,
                isDarkTheme = isDarkTheme,
                content = {
                    Text(
                        keyboardState.turns.value.toString(),
                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = viewModel::switchWideTurn,
                isDarkTheme = isDarkTheme,
                content = {
                    Text(
                        "w",
                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = { viewModel.deleteText(context) },
                isDarkTheme = isDarkTheme,
                content = {
                    Text(
                        "⌫",
                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            ControlKeyButton(
                modifier = controlKeyModifier,
                onClick = { viewModel.inputText(context, "\n") },
                isDarkTheme = isDarkTheme,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_return),
                        tint = if (isDarkTheme) Color.White else Color.Black,
                        contentDescription = "Language"
                    )
                }
            )
        }
    }
}

@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
fun HushKeyboardPreview() {
    val viewModel = KeyboardViewModel()

    HushKeyboard(
        viewModel = viewModel
    )
}
