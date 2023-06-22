package com.rickyhu.hushkeyboard

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.rickyhu.hushkeyboard.ui.composables.HushKeyboard

class HushKeyboardView(context: Context) : AbstractComposeView(context) {
    @Composable
    override fun Content() = HushKeyboard()
}
