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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.settings.SettingsState
import com.rickyhu.hushkeyboard.settings.SettingsViewModel
import com.rickyhu.hushkeyboard.ui.settings.composables.AddSpaceBetweenNotationSwitchItem
import com.rickyhu.hushkeyboard.ui.settings.composables.AppVersionItem
import com.rickyhu.hushkeyboard.ui.settings.composables.ThemeOptionDropdownItem
import com.rickyhu.hushkeyboard.ui.settings.composables.VibrateOnTapSwitchItem
import com.rickyhu.hushkeyboard.ui.settings.composables.WideNotationOptionDropdownItem
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.settingsState.collectAsState(SettingsState())

    SettingsContent(
        state,
        onThemeSelected = viewModel::updateThemeOption,
        onWideNotationOptionSelected = viewModel::updateWideNotationOption,
        onAddSpaceBetweenNotationChanged = viewModel::updateAddSpaceBetweenNotation,
        onVibrateOnTapChanged = viewModel::updateVibrateOnTap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    state: SettingsState,
    onThemeSelected: (themeOption: ThemeOption) -> Unit,
    onWideNotationOptionSelected: (wideNotationOption: WideNotationOption) -> Unit,
    onAddSpaceBetweenNotationChanged: (addSpaceAfterNotation: Boolean) -> Unit,
    onVibrateOnTapChanged: (vibrateOnTap: Boolean) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ThemeOptionDropdownItem(
                    currentTheme = state.themeOption,
                    onThemeSelected = onThemeSelected
                )
                WideNotationOptionDropdownItem(
                    currentOption = state.wideNotationOption,
                    onOptionSelected = onWideNotationOptionSelected
                )
                AddSpaceBetweenNotationSwitchItem(
                    value = state.addSpaceAfterNotation,
                    onValueChanged = onAddSpaceBetweenNotationChanged
                )
                VibrateOnTapSwitchItem(
                    value = state.vibrateOnTap,
                    onValueChanged = onVibrateOnTapChanged
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
        SettingsScreen()
    }
}
