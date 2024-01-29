package com.rickyhu.hushkeyboard.ui.settings.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.settings.ThemeOption
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDropdownTile(
    currentTheme: ThemeOption,
    onThemeSelected: (ThemeOption) -> Unit
) {
    ListItem(
        headlineText = { Text("System Theme") },
        leadingContent = {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
        },
        trailingContent = {
            DropdownButton(
                currentTheme = currentTheme,
                onThemeSelected = onThemeSelected
            )
        }
    )
}

@Composable
private fun DropdownButton(
    currentTheme: ThemeOption,
    onThemeSelected: (ThemeOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        for (option in ThemeOption.values()) {
            DropdownMenuItem(
                text = { Text(option.name) },
                onClick = {
                    onThemeSelected(option)
                    expanded = false
                }
            )
        }
    }

    OutlinedButton(
        onClick = { expanded = true }
    ) {
        Text(text = currentTheme.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeSelectionDropdownTilePreview() {
    HushKeyboardTheme {
        ThemeSelectionDropdownTile(
            currentTheme = ThemeOption.SYSTEM,
            onThemeSelected = {}
        )
    }
}
