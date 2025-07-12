package com.rickyhu.hushkeyboard.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun AddSpaceBetweenNotationSwitchItem(
    value: Boolean,
    onValueChanged: (Boolean) -> Unit = {},
) {
    ListItem(
        modifier = Modifier.clickable { onValueChanged(!value) },
        headlineContent = { Text("Add space after notation") },
        leadingContent = {
            Icon(
                painter = painterResource(R.drawable.ic_space),
                contentDescription = "Space",
            )
        },
        trailingContent = {
            Switch(
                checked = value,
                onCheckedChange = onValueChanged,
                modifier = Modifier.testTag("AddSpaceAfterNotationSwitch"),
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun AddSpaceBetweenNotationSwitchItemPreview() {
    HushKeyboardTheme {
        AddSpaceBetweenNotationSwitchItem(
            value = true,
        )
    }
}
