package com.rickyhu.hushkeyboard.keyboard.ui.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.ui.theme.DarkSecondary
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme
import com.rickyhu.hushkeyboard.ui.theme.LightSecondary

@Composable
fun ControlKeyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    KeyButton(
        modifier = modifier.clickable(onClick = onClick),
        buttonColor = if (isDarkTheme) DarkSecondary else LightSecondary,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun ControlKeyButtonPreview() {
    HushKeyboardTheme {
        ControlKeyButton(
            modifier = Modifier.size(48.dp),
            isDarkTheme = false,
            onClick = {},
            content = {
                Text(
                    text = "R",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}
