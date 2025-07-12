package com.rickyhu.hushkeyboard.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.data.WideNotationOption
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun WideNotationOptionDropdownItem(
    currentOption: WideNotationOption,
    onOptionSelected: (WideNotationOption) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ListItem(
        modifier = Modifier.clickable { expanded = true },
        headlineContent = { Text("Wide notation") },
        leadingContent = {
            Icon(
                painter = painterResource(R.drawable.ic_keyboard),
                contentDescription = "Keyboard",
            )
        },
        trailingContent = {
            Text(text = currentOption.toString())

            DropdownMenu(
                modifier = Modifier.testTag("WideNotationOptionDropdownMenu"),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                for (option in WideNotationOption.values()) {
                    DropdownMenuItem(
                        modifier = Modifier.testTag("WideNotationOptionDropdownMenuItem"),
                        text = { Text(option.toString()) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun WideNotationOptionDropdownItemPreview() {
    HushKeyboardTheme {
        WideNotationOptionDropdownItem(
            currentOption = WideNotationOption.WideWithW,
            onOptionSelected = {},
        )
    }
}
