package com.rickyhu.hushkeyboard.utils

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputConnection
import com.rickyhu.hushkeyboard.model.Notation
import com.rickyhu.hushkeyboard.service.HushIMEService

private const val END_CURSOR_POSITION = 1
private const val MAX_NOTATION_LENGTH = 5

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

    beginBatchEdit()
    if (!getSelectedText(0).isNullOrEmpty()) {
        deleteSurroundingText(1, 0)
        endBatchEdit()
        return
    }

    while (true) {
        val endTextChunk = getTextBeforeCursor(MAX_NOTATION_LENGTH, 0)
        if (endTextChunk.isNullOrEmpty()) break

        for (i in endTextChunk.indices.reversed()) {
            val char = endTextChunk[i]
            if (char in notationCharList) {
                deleteSurroundingText(MAX_NOTATION_LENGTH, 0)
                commitText(endTextChunk.substring(0, i), END_CURSOR_POSITION)
                endBatchEdit()
                return
            }
        }

        deleteSurroundingText(MAX_NOTATION_LENGTH, 0)
    }

    endBatchEdit()
}
