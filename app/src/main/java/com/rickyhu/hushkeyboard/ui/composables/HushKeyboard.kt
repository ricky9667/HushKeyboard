package com.rickyhu.hushkeyboard.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.model.Notation

@Composable
fun HushKeyboard() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val keyboardKeys = listOf(
            listOf(
                CubeKey(Notation.R),
                CubeKey(Notation.U),
                CubeKey(Notation.F),
                CubeKey(Notation.L),
                CubeKey(Notation.D),
                CubeKey(Notation.B)
            ),
            listOf(
                CubeKey(Notation.M),
                CubeKey(Notation.E),
                CubeKey(Notation.S),
                CubeKey(Notation.X),
                CubeKey(Notation.Y),
                CubeKey(Notation.Z)
            )
        )

        for (keysRow in keyboardKeys) {
            KeyButtonsRow(keysRow)
        }
    }
}

@Composable
fun KeyButtonsRow(keys: List<CubeKey>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        keys.forEach { key -> KeyButton(key) }
    }
}
