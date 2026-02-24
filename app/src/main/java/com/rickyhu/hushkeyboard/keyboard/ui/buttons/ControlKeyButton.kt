package com.rickyhu.hushkeyboard.keyboard.ui.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun ControlKeyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    KeyButton(
        modifier = modifier.clickable(onClick = onClick),
        buttonColor = MaterialTheme.colorScheme.secondary,
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
fun ControlKeyButtonPreview() {
    HushKeyboardTheme {
        ControlKeyButton(
            modifier = Modifier.size(48.dp),
            onClick = {},
            content = {
                Text(
                    text = "R",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
}
