package com.rickyhu.hushkeyboard.ui.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.model.CubeKey
import com.rickyhu.hushkeyboard.ui.keyboard.buttons.NotationKeyButton

@Composable
fun NotationKeyButtonsRow(
    keys: List<CubeKey>,
    isDarkTheme: Boolean = false,
    onTextInput: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        keys.forEach { key ->
            NotationKeyButton(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp),
                cubeKey = key,
                isDarkTheme = isDarkTheme,
                onTextInput = onTextInput
            )
        }
    }
}
