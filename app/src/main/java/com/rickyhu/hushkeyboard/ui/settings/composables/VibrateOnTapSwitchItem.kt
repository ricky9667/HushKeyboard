package com.rickyhu.hushkeyboard.ui.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VibrateOnTapSwitchItem(
    value: Boolean,
    onValueChanged: (Boolean) -> Unit = {}
) {
    ListItem(
        modifier = Modifier.clickable { onValueChanged(!value) },
        headlineText = { Text("Vibrate on tap") },
        leadingContent = {
            Icon(
                painter = painterResource(R.drawable.ic_vibration),
                contentDescription = "Vibrate"
            )
        },
        trailingContent = {
            Switch(checked = value, onCheckedChange = onValueChanged)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun VibrateOnTapSwitchItemPreview() {
    HushKeyboardTheme {
        VibrateOnTapSwitchItem(
            value = true
        )
    }
}
