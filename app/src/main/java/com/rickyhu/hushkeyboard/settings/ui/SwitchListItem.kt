package com.rickyhu.hushkeyboard.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun SwitchListItem(
    modifier: Modifier = Modifier,
    title: String,
    leading: @Composable () -> Unit,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit = {},
    switchTestTag: String? = null,
) {
    ListItem(
        modifier = modifier.clickable { onValueChanged(!value) },
        headlineContent = { Text(title) },
        leadingContent = leading,
        trailingContent = {
            Switch(
                checked = value,
                onCheckedChange = onValueChanged,
                modifier = Modifier.testTag(switchTestTag ?: title),
            )
        },
    )
}
