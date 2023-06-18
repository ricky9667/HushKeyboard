package com.rickyhu.hushkeyboard.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Composable
fun KeyButton(key: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(48.dp)
            .clickable {
                Log.d("KeyButton", "Clicked $key")
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = key,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeyButtonPreview() {
    HushKeyboardTheme {
        KeyButton("R")
    }
}
