package com.rickyhu.hushkeyboard.ui.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppVersionItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ListItem(
        headlineContent = { Text("Version") },
        leadingContent = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") },
        trailingContent = { Text("v0.2.0") },
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview(showBackground = true)
@Composable
private fun AppVersionItemPreview() {
    HushKeyboardTheme {
        AppVersionItem()
    }
}
