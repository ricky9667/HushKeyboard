package com.rickyhu.hushkeyboard.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun AppVersionItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val packageName = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageName.versionName ?: "Unknown Version"

    ListItem(
        headlineContent = { Text("Version") },
        leadingContent = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") },
        trailingContent = { Text(versionName) },
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
