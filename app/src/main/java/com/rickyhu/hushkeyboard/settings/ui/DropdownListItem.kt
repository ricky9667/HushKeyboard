package com.rickyhu.hushkeyboard.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun <T> DropdownListItem(
    modifier: Modifier = Modifier,
    title: String,
    leading: @Composable () -> Unit,
    currentValue: T,
    onValueSelected: (T) -> Unit,
    options: List<T>,
    displayText: (T) -> String = { it.toString() },
    optionContent: (@Composable (T) -> Unit)? = null,
    menuTestTag: String? = null,
    menuItemTestTag: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    ListItem(
        modifier = modifier.clickable { expanded = true },
        headlineContent = { Text(title) },
        leadingContent = leading,
        trailingContent = {
            if (optionContent != null) {
                optionContent(currentValue)
            } else {
                Text(text = displayText(currentValue))
            }

            DropdownMenu(
                modifier = Modifier.testTag(menuTestTag ?: title),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                for (option in options) {
                    DropdownMenuItem(
                        modifier = Modifier.testTag(menuItemTestTag ?: title),
                        text = {
                            if (optionContent != null) {
                                optionContent(option)
                            } else {
                                Text(displayText(option))
                            }
                        },
                        onClick = {
                            onValueSelected(option)
                            expanded = false
                        },
                    )
                }
            }
        },
    )
}
