package com.rickyhu.hushkeyboard.ui.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Destination
@Composable
fun SettingsScreen() = SettingsContent()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        },
        content = { padding ->
            Text("Settings", modifier = Modifier.padding(padding))
        }
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    HushKeyboardTheme {
        SettingsContent()
    }
}
