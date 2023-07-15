package com.rickyhu.hushkeyboard.model

object NotationKeyProvider {
    fun getFirstRowKeys(
        isCounterClockwise: Boolean = false,
        turns: Turns = Turns.Single,
        isWideTurn: Boolean = false
    ): List<CubeKey> = listOf(
        CubeKey(Notation.R, isCounterClockwise, turns, isWideTurn),
        CubeKey(Notation.U, isCounterClockwise, turns, isWideTurn),
        CubeKey(Notation.F, isCounterClockwise, turns, isWideTurn),
        CubeKey(Notation.L, isCounterClockwise, turns, isWideTurn),
        CubeKey(Notation.D, isCounterClockwise, turns, isWideTurn),
        CubeKey(Notation.B, isCounterClockwise, turns, isWideTurn)
    )

    fun getSecondRowKeys(
        isCounterClockwise: Boolean = false,
        turns: Turns = Turns.Single
    ): List<CubeKey> = listOf(
        CubeKey(Notation.M, isCounterClockwise, turns),
        CubeKey(Notation.E, isCounterClockwise, turns),
        CubeKey(Notation.S, isCounterClockwise, turns),
        CubeKey(Notation.X, isCounterClockwise, turns),
        CubeKey(Notation.Y, isCounterClockwise, turns),
        CubeKey(Notation.Z, isCounterClockwise, turns)
    )
}
