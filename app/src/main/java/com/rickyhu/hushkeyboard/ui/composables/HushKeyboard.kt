package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HushKeyboard() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        KeyButton("R")
    }
}
