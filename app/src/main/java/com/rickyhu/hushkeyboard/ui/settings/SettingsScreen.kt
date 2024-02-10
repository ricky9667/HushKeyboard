package com.rickyhu.hushkeyboard.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.dataStore
import com.rickyhu.hushkeyboard.ui.settings.composables.AddSpaceBetweenNotationSwitchItem
import com.rickyhu.hushkeyboard.ui.settings.composables.AppVersionItem
import com.rickyhu.hushkeyboard.ui.settings.composables.SystemThemeDropdownItem
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Destination
@Composable
fun SettingsScreen() = SettingsContent()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val context = LocalContext.current
    val settings by context.dataStore.data.collectAsState(initial = AppSettings())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SystemThemeDropdownItem(
                    currentTheme = settings.themeOption,
                    onThemeSelected = { themeOption ->
                        coroutineScope.launch {
                            context.dataStore.updateData { settings ->
                                settings.copy(themeOption = themeOption)
                            }
                        }
                    }
                )
                AddSpaceBetweenNotationSwitchItem(
                    value = settings.addSpaceAfterNotation,
                    onValueChanged = { addSpaceAfterNotation ->
                        coroutineScope.launch {
                            context.dataStore.updateData { settings ->
                                settings.copy(addSpaceAfterNotation = addSpaceAfterNotation)
                            }
                        }
                    }
                )
                AppVersionItem(
                    onClick = {
                        val url = "https://github.com/ricky9667/HushKeyboard"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
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
