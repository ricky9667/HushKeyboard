package com.rickyhu.hushkeyboard.model

object NotationKeyProvider {
    fun getFirstRowKeys(config: CubeKey.Config): List<CubeKey> = listOf(
        CubeKey(Notation.R, config),
        CubeKey(Notation.U, config),
        CubeKey(Notation.F, config),
        CubeKey(Notation.L, config),
        CubeKey(Notation.D, config),
        CubeKey(Notation.B, config)
    )

    fun getSecondRowKeys(config: CubeKey.Config): List<CubeKey> {
        // Middle layer keys and cube rotations don't have wide turns.
        val secondRowConfig = config.copy(isWideTurn = false)

        return listOf(
            CubeKey(Notation.M, secondRowConfig),
            CubeKey(Notation.E, secondRowConfig),
            CubeKey(Notation.S, secondRowConfig),
            CubeKey(Notation.X, secondRowConfig),
            CubeKey(Notation.Y, secondRowConfig),
            CubeKey(Notation.Z, secondRowConfig)
        )
    }
}
