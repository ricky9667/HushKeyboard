package com.rickyhu.hushkeyboard

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView

class HushKeyboardView(context: Context) : AbstractComposeView(context) {
    @Composable
    override fun Content() {
        Text("Hush Keyboard")
    }
}
