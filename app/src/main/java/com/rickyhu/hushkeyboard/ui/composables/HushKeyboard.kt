package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HushKeyboard() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val keyboardKeys = listOf(
            listOf("R", "U", "F", "L", "D", "B")
        )

        for (keysRow in keyboardKeys) {
            KeyButtonsRow(keysRow)
        }
    }
}

@Composable
fun KeyButtonsRow(keys: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        keys.forEach { KeyButton(it) }
    }
}
