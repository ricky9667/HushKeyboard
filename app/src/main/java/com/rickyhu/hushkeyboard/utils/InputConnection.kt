package com.rickyhu.hushkeyboard.utils

import android.content.Context
import android.view.inputmethod.InputConnection
import com.rickyhu.hushkeyboard.service.HushIMEService

private const val CURSOR_POSITION = 1

fun Context.toInputConnection(): InputConnection = (this as HushIMEService).currentInputConnection

fun InputConnection.inputText(text: String) {
    commitText(text, CURSOR_POSITION)
}

fun InputConnection.deleteText() {
    val selectedText = getSelectedText(0)

    if (selectedText.isNullOrEmpty()) {
        deleteSurroundingText(1, 0)
    } else {
        commitText("", CURSOR_POSITION)
    }
}
