package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Composable
fun ControlKeyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    KeyButton(
        modifier = modifier.clickable(onClick = onClick),
        text = text
    )
}

@Preview(showBackground = true)
@Composable
fun ControlKeyButtonPreview() {
    HushKeyboardTheme {
        ControlKeyButton(
            modifier = Modifier.size(48.dp),
            text = "'",
            onClick = {}
        )
    }
}
