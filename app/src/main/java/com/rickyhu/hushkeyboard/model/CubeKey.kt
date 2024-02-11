package com.rickyhu.hushkeyboard.model

import com.rickyhu.hushkeyboard.settings.WideNotationOption

data class CubeKey(
    val notation: Notation,
    val isCounterClockwise: Boolean = false,
    val turns: Turns = Turns.Single,
    val isWideTurn: Boolean = false
) {
    fun asText(
        addSpaceAfterNotation: Boolean,
        wideNotationOption: WideNotationOption
    ): String {
        var result = notation.value

        if (isWideTurn) {
            result = when (wideNotationOption) {
                WideNotationOption.WideWithW -> "${result}w"
                WideNotationOption.WideWithLowercase -> result.lowercase()
            }
        }

        result += turns.toString()

        if (isCounterClockwise) result += "'"

        return if (addSpaceAfterNotation) "$result " else result
    }
}
