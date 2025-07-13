package com.rickyhu.hushkeyboard.model

import com.rickyhu.hushkeyboard.data.WideNotationOption

data class CubeKey(
    val notation: Notation,
    val config: Config = Config(),
) {
    data class Config(
        val isCounterClockwise: Boolean = false,
        val turns: Turns = Turns.Single,
        val isWideTurn: Boolean = false,
    )

    fun asText(
        addSpaceAfterNotation: Boolean,
        wideNotationOption: WideNotationOption,
    ): String {
        var result = notation.value

        if (config.isWideTurn) {
            result =
                when (wideNotationOption) {
                    WideNotationOption.WideWithW -> "${result}w"
                    WideNotationOption.WideWithLowercase -> result.lowercase()
                }
        }

        result += config.turns.toString()

        if (config.isCounterClockwise) result += "'"

        return if (addSpaceAfterNotation) "$result " else result
    }
}
