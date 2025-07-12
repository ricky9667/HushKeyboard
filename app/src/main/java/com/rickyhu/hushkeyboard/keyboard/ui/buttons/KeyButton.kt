package com.rickyhu.hushkeyboard.keyboard.ui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun KeyButton(
    modifier: Modifier = Modifier,
    buttonColor: Color,
    content: @Composable () -> Unit,
) {
    Card(modifier = modifier) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(buttonColor)
                    .padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeyButtonPreview() {
    HushKeyboardTheme {
        KeyButton(
            modifier = Modifier.size(48.dp),
            buttonColor = Color.White,
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
