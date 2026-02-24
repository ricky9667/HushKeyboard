package com.rickyhu.hushkeyboard.settings.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
fun UrlLauncherListItem(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    leading: @Composable () -> Unit,
    trailing: (@Composable () -> Unit)? = null,
) {
    val context = LocalContext.current

    ListItem(
        headlineContent = { Text(title) },
        leadingContent = leading,
        trailingContent = trailing,
        modifier =
            modifier.clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    context.startActivity(intent)
                },
            ),
    )
}
