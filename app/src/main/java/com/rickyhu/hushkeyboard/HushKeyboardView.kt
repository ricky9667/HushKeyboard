package com.rickyhu.hushkeyboard

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.rickyhu.hushkeyboard.ui.keyboard.HushKeyboard
import com.rickyhu.hushkeyboard.viewmodel.KeyboardViewModel

class HushKeyboardView(context: Context) : AbstractComposeView(context) {
    private var viewModel = KeyboardViewModel()

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    override fun Content() = HushKeyboard(viewModel)
}
