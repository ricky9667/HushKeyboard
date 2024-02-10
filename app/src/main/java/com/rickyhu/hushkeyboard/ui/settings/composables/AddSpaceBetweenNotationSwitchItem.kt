package com.rickyhu.hushkeyboard.ui.settings.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSpaceBetweenNotationSwitchItem(
    value: Boolean,
    onValueChanged: (Boolean) -> Unit = {}
) {
    ListItem(
        headlineText = { Text("Add space after notation") },
        leadingContent = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") },
        trailingContent = {
            Switch(checked = value, onCheckedChange = onValueChanged)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddSpaceBetweenNotationSwitchItemPreview() {
    HushKeyboardTheme {
        AddSpaceBetweenNotationSwitchItem(
            value = true
        )
    }
}
