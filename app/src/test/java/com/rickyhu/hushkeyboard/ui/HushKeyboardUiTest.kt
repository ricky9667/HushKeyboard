package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rickyhu.hushkeyboard.keyboard.HushKeyboardContent
import com.rickyhu.hushkeyboard.keyboard.KeyboardState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HushKeyboardUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HushKeyboardContent(state = KeyboardState())
        }
    }

    @Test
    fun `First row key should exist, should be enabled, and should have click action`() {
        keyButtonShouldBe("R ")
    }

    @Test
    fun `First row key should show ' notation after clicking counter clockwise button`() {
        clickCounterClockwiseButton()
        keyButtonShouldBe("R' ")

        clickCounterClockwiseButton()
        keyButtonShouldBe("R ")
    }

    @Test
    fun `First row key should show w notation after clicking wide notation button`() {
        clickWideNotationButton()
        keyButtonShouldBe("Rw ")
    }

    @Test
    fun `First row key should show correct turn degree after clicking turn degree button`() {
        clickTurnDegreeButton()
        keyButtonShouldBe("R2 ")

        clickTurnDegreeButton()
        keyButtonShouldBe("R3 ")

        clickTurnDegreeButton()
        keyButtonShouldBe("R ")
    }

    private fun keyButtonShouldBe(text: String) {
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun clickCounterClockwiseButton() {
        composeTestRule
            .onNodeWithTag("RotateDirectionButton")
            .performClick()
    }

    private fun clickTurnDegreeButton() {
        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .performClick()
    }

    private fun clickWideNotationButton() {
        composeTestRule
            .onNodeWithTag("WideTurnButton")
            .performClick()
    }
}
