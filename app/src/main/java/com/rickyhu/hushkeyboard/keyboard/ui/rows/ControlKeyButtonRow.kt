package com.rickyhu.hushkeyboard.keyboard.ui.rows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.keyboard.ui.buttons.ControlKeyButton
import com.rickyhu.hushkeyboard.model.Turns
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun ControlKeyButtonRow(
    modifier: Modifier = Modifier,
    turns: Turns,
    isDarkTheme: Boolean,
    smartDelete: Boolean,
    inputMethodButtonAction: () -> Unit,
    rotateDirectionButtonAction: () -> Unit,
    turnDegreeButtonAction: () -> Unit,
    wideTurnButtonAction: () -> Unit,
    deleteButtonAction: () -> Unit,
    newLineButtonAction: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        val keyColor = if (isDarkTheme) Color.White else Color.Black
        val controlKeyModifier = Modifier
            .padding(4.dp)
            .size(48.dp)

        // TODO: unify button colors
        ControlKeyButton(
            modifier = controlKeyModifier.testTag("InputMethodButton"),
            onClick = inputMethodButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_language),
                    tint = keyColor,
                    contentDescription = "Language"
                )
            }
        )
        ControlKeyButton(
            modifier = controlKeyModifier.testTag("RotateDirectionButton"),
            onClick = rotateDirectionButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Text(
                    "'",
                    color = keyColor,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        ControlKeyButton(
            modifier = controlKeyModifier.testTag("TurnDegreeButton"),
            onClick = turnDegreeButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Text(
                    turns.value.toString(),
                    color = keyColor,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        ControlKeyButton(
            modifier = controlKeyModifier.testTag("WideTurnButton"),
            onClick = wideTurnButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Text(
                    "w",
                    color = keyColor,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )

        ControlKeyButton(
            modifier = controlKeyModifier.testTag("DeleteButton"),
            onClick = deleteButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Icon(
                    painter = if (smartDelete) {
                        painterResource(R.drawable.ic_backspace_filled)
                    } else {
                        painterResource(R.drawable.ic_backspace_outlined)
                    },
                    tint = keyColor,
                    contentDescription = "Delete"
                )
            }
        )
        ControlKeyButton(
            modifier = controlKeyModifier.testTag("NewLineButton"),
            onClick = newLineButtonAction,
            isDarkTheme = isDarkTheme,
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_return),
                    tint = keyColor,
                    contentDescription = "Return"
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ControlKeyButtonRowPreview() {
    HushKeyboardTheme {
        ControlKeyButtonRow(
            turns = Turns.Single,
            isDarkTheme = false,
            smartDelete = true,
            inputMethodButtonAction = {},
            rotateDirectionButtonAction = {},
            turnDegreeButtonAction = {},
            wideTurnButtonAction = {},
            deleteButtonAction = {},
            newLineButtonAction = {}
        )
    }
}
