package com.rickyhu.hushkeyboard.ui.settings

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.ramcosta.composedestinations.annotation.Destination
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.ui.keyboard.dataStore
import com.rickyhu.hushkeyboard.ui.settings.composables.ThemeSelectionDropdownTile
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme
import kotlinx.coroutines.launch

@Destination
@Composable
fun SettingsScreen() = SettingsContent()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent() {
    val context = LocalContext.current
    val settingsState = context.dataStore.data.collectAsState(initial = AppSettings())

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SettingsMenuLink(
                    icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") },
                    title = { Text("Version") },
                    subtitle = { Text("v0.1.0") },
                    onClick = {
                        val url = "https://github.com/ricky9667/HushKeyboard"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
                ThemeSelectionDropdownTile(
                    onThemeSelected = {
                        Log.d("SettingsScreen", "Theme selected: $it")

                        scope.launch {
                            context.dataStore.updateData { settings ->
                                settings.copy(
                                    systemTheme = it
                                )
                            }
                        }
                    }
                )
            }
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
