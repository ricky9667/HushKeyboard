package com.rickyhu.hushkeyboard.model

data class CubeKey(
    val notation: Notation,
    val isCounterClockwise: Boolean = false,
    val turns: Turns = Turns.Single,
    val isWideTurn: Boolean = false
) {
    override fun toString(): String {
        var result = notation.value

        if (isWideTurn) result += "w"
        result += turns.toString()
        if (isCounterClockwise) result += "'"

        return result
    }

    fun asText(addSpaceAfterNotation: Boolean): String {
        val result = toString()
        return if (addSpaceAfterNotation) "$result " else result
    }
}
