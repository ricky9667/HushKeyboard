package com.rickyhu.hushkeyboard.utils

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputConnection
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.service.HushIMEService

private const val TAG = "InputConnection"
private const val NEWLINE = '\n'
private const val END_CURSOR_POSITION = 1
private const val SCAN_WINDOW_SIZE = 50

val notationCharList = Notation.getCharList() + listOf(NEWLINE)

fun Context.toInputConnection(): InputConnection = (this as HushIMEService).currentInputConnection

fun InputConnection.inputText(text: String) {
    commitText(text, END_CURSOR_POSITION)
    Log.d(TAG, "inputText $text")
}

fun InputConnection.inputNewline() {
    inputText(NEWLINE.toString())
}

fun InputConnection.deleteText() {
    val selectedText = getSelectedText(0)

    if (selectedText.isNullOrEmpty()) {
        deleteSurroundingText(1, 0)
        Log.d(TAG, "deleteText")
    } else {
        commitText("", END_CURSOR_POSITION)
        Log.d(TAG, "delete selected text: $selectedText")
    }
}

fun InputConnection.smartDelete() {
    Log.d(TAG, "smartDelete")

    val selectedText = getSelectedText(0)
    if (!selectedText.isNullOrEmpty()) {
        commitText("", END_CURSOR_POSITION)
        Log.d(TAG, "delete selected text: $selectedText")
        return
    }

    beginBatchEdit()
    try {
        val textBeforeCursor = getTextBeforeCursor(SCAN_WINDOW_SIZE, 0)

        if (textBeforeCursor.isNullOrEmpty()) {
            deleteSurroundingText(1, 0)
            return
        }

        var charsToDelete = 0
        for (i in textBeforeCursor.indices.reversed()) {
            val char = textBeforeCursor[i]
            charsToDelete++
            if (char.uppercaseChar() in notationCharList) {
                break
            }
        }

        if (charsToDelete > 0) {
            deleteSurroundingText(charsToDelete, 0)
        }
    } finally {
        endBatchEdit()
    }
}
