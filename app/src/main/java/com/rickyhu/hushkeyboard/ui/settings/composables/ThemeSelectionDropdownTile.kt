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
import com.rickyhu.hushkeyboard.settings.SystemTheme
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDropdownTile(
    onThemeSelected: (SystemTheme) -> Unit
) {
    ListItem(
        headlineText = { Text("System Theme") },
        leadingContent = {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
        },
        trailingContent = {
            DropdownButton(onThemeSelected = onThemeSelected)
        }
    )
}

@Composable
private fun DropdownButton(
    onThemeSelected: (SystemTheme) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf(SystemTheme.SYSTEM) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("System") },
            onClick = {
                selectedTheme = SystemTheme.SYSTEM
                onThemeSelected(SystemTheme.SYSTEM)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text("Light") },
            onClick = {
                selectedTheme = SystemTheme.LIGHT
                onThemeSelected(SystemTheme.LIGHT)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text("Dark") },
            onClick = {
                selectedTheme = SystemTheme.DARK
                onThemeSelected(SystemTheme.DARK)
                expanded = false
            }
        )
    }

    OutlinedButton(
        onClick = { expanded = true }
    ) {
        Text(text = selectedTheme.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeSelectionDropdownTilePreview() {
    HushKeyboardTheme {
        ThemeSelectionDropdownTile(
            onThemeSelected = {}
        )
    }
}