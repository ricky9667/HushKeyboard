package com.rickyhu.hushkeyboard.model

import com.rickyhu.hushkeyboard.data.WideNotationOption
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CubeKeyTest {
    @Test
    fun `Default cube key should be clockwise, single turn, and not wide turn`() {
        val cubeKey = CubeKey(Notation.R)
        val actualText = cubeKey.asText(false, WideNotationOption.WideWithW)

        assertEquals("R", actualText)
    }
}
