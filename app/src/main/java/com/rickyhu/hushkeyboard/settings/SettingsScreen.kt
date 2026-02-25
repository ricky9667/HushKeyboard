package com.rickyhu.hushkeyboard.settings

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.data.KeyboardColorOption
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.settings.ui.DropdownListItem
import com.rickyhu.hushkeyboard.settings.ui.SwitchListItem
import com.rickyhu.hushkeyboard.settings.ui.UrlLauncherListItem
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinViewModel()) {
    val state by viewModel.settingsState.collectAsState(SettingsState())

    SettingsContent(
        state,
        onThemeSelected = viewModel::updateThemeOption,
        onKeyboardColorOptionSelected = viewModel::updateKeyboardColorOption,
        onWideNotationOptionSelected = viewModel::updateWideNotationOption,
        onSmartDeleteChanged = viewModel::updateSmartDelete,
        onAddSpaceBetweenNotationChanged = viewModel::updateAddSpaceBetweenNotation,
        onVibrateOnTapChanged = viewModel::updateVibrateOnTap,
    )
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(
    state: SettingsState,
    onThemeSelected: (themeOption: ThemeOption) -> Unit,
    onKeyboardColorOptionSelected: (keyboardColorOption: KeyboardColorOption) -> Unit,
    onWideNotationOptionSelected: (wideNotationOption: WideNotationOption) -> Unit,
    onSmartDeleteChanged: (smartDelete: Boolean) -> Unit,
    onAddSpaceBetweenNotationChanged: (addSpaceAfterNotation: Boolean) -> Unit,
    onVibrateOnTapChanged: (vibrateOnTap: Boolean) -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
            )
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                DropdownListItem(
                    title = stringResource(R.string.app_theme),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_brightness),
                            contentDescription = stringResource(R.string.app_theme),
                        )
                    },
                    currentValue = state.themeOption,
                    onValueSelected = onThemeSelected,
                    options = ThemeOption.entries.toList(),
                    displayText = { it.name },
                    menuTestTag = "ThemeOptionDropdownMenu",
                    menuItemTestTag = "ThemeOptionDropdownMenuItem",
                )

                DropdownListItem(
                    title = stringResource(R.string.keyboard_color),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_palette),
                            contentDescription = stringResource(R.string.keyboard_color),
                        )
                    },
                    currentValue = state.keyboardColorOption,
                    onValueSelected = onKeyboardColorOptionSelected,
                    options = KeyboardColorOption.entries.toList(),
                    optionContent = { option ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(option.color),
                            )
                            Text(option.toString())
                        }
                    },
                    menuTestTag = "KeyboardColorOptionDropdownMenu",
                    menuItemTestTag = "KeyboardColorOptionDropdownMenuItem",
                )

                DropdownListItem(
                    title = stringResource(R.string.wide_notation),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_keyboard),
                            contentDescription = stringResource(R.string.wide_notation),
                        )
                    },
                    currentValue = state.wideNotationOption,
                    onValueSelected = onWideNotationOptionSelected,
                    options = WideNotationOption.entries.toList(),
                    displayText = { it.toString() },
                    menuTestTag = "WideNotationOptionDropdownMenu",
                    menuItemTestTag = "WideNotationOptionDropdownMenuItem",
                )

                SwitchListItem(
                    title = stringResource(R.string.smart_delete),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_backspace_filled),
                            contentDescription = stringResource(R.string.smart_delete),
                        )
                    },
                    value = state.smartDelete,
                    onValueChanged = onSmartDeleteChanged,
                    switchTestTag = "SmartDeleteSwitchItem",
                )

                SwitchListItem(
                    title = stringResource(R.string.add_space_after_notation),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_space),
                            contentDescription = stringResource(R.string.add_space_after_notation),
                        )
                    },
                    value = state.addSpaceAfterNotation,
                    onValueChanged = onAddSpaceBetweenNotationChanged,
                    switchTestTag = "AddSpaceAfterNotationSwitch",
                )

                SwitchListItem(
                    title = stringResource(R.string.vibrate_on_tap),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_vibration),
                            contentDescription = stringResource(R.string.vibrate_on_tap),
                        )
                    },
                    value = state.vibrateOnTap,
                    onValueChanged = onVibrateOnTapChanged,
                    switchTestTag = "VibrateOnTapSwitch",
                )

                HorizontalDivider()

                val packageName = context.packageManager.getPackageInfo(context.packageName, 0)
                val versionName =
                    packageName.versionName ?: stringResource(R.string.version_unknown)
                UrlLauncherListItem(
                    title = stringResource(R.string.version),
                    url = stringResource(R.string.hush_keyboard_github),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_info),
                            contentDescription = stringResource(R.string.version),
                        )
                    },
                    trailing = { Text(versionName) },
                )

                UrlLauncherListItem(
                    title = stringResource(R.string.donate),
                    url = stringResource(R.string.buy_me_a_coffee_url),
                    leading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_favorite),
                            contentDescription = stringResource(R.string.donate),
                        )
                    },
                )
            }
        },
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    HushKeyboardTheme {
        SettingsScreen()
    }
}
