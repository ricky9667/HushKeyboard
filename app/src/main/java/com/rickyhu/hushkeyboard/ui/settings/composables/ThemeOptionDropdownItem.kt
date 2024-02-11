package com.rickyhu.hushkeyboard.ui.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.settings.ThemeOption
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemThemeDropdownItem(
    currentTheme: ThemeOption,
    onThemeSelected: (ThemeOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ListItem(
        modifier = Modifier.clickable { expanded = true },
        headlineText = { Text("System Theme") },
        leadingContent = {
            Icon(
                painter = painterResource(R.drawable.ic_brightness),
                contentDescription = "Brightness"
            )
        },
        trailingContent = {
            Text(text = currentTheme.name)

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
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SystemThemeDropdownItemPreview() {
    HushKeyboardTheme {
        SystemThemeDropdownItem(
            currentTheme = ThemeOption.System,
            onThemeSelected = {}
        )
    }
}
