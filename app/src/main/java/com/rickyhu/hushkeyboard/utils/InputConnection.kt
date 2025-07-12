package com.rickyhu.hushkeyboard.utils

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputConnection
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.service.HushIMEService

private const val END_CURSOR_POSITION = 1

val notationCharList = Notation.getCharList() + listOf('\n')

fun Context.toInputConnection(): InputConnection = (this as HushIMEService).currentInputConnection

fun InputConnection.inputText(text: String) {
    commitText(text, END_CURSOR_POSITION)
    Log.d("InputConnection", "inputText $text")
}

fun InputConnection.deleteText() {
    val selectedText = getSelectedText(0)

    if (selectedText.isNullOrEmpty()) {
        deleteSurroundingText(1, 0)
        Log.d("InputConnection", "deleteText")
    } else {
        commitText("", END_CURSOR_POSITION) // delete selected text
        Log.d("InputConnection", "deleteText $selectedText")
    }
}

fun InputConnection.smartDelete() {
    Log.d("InputConnection", "smartDelete")
    if (!getSelectedText(0).isNullOrEmpty()) {
        commitText("", END_CURSOR_POSITION)
        return
    }

    beginBatchEdit()
    try {
        val scanWindow = 50
        val textBeforeCursor = getTextBeforeCursor(scanWindow, 0)

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
