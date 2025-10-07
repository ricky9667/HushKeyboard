package com.rickyhu.hushkeyboard.utils

import org.worldcubeassociation.tnoodle.scrambles.PuzzleRegistry
import java.security.NoSuchProviderException
import java.security.SecureRandom

object ScrambleProvider {
    val random: SecureRandom =
        try {
            SecureRandom.getInstance("SHA1PRNG", "AndroidOpenSSL")
        } catch (e: NoSuchProviderException) {
            SecureRandom() // Fallback to default
        }

    fun getScramble(puzzleType: PuzzleRegistry): String = puzzleType.scrambler.generateWcaScramble(random)
}
