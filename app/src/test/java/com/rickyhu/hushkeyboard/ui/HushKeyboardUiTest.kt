package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
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

    @Test
    fun `Second row key should exist, should be enabled, and should have click action`() {
        keyButtonShouldBe("M ")
    }

    @Test
    fun `Second row key should show ' notation after clicking counter clockwise button`() {
        clickCounterClockwiseButton()
        keyButtonShouldBe("M' ")

        clickCounterClockwiseButton()
        keyButtonShouldBe("M ")
    }

    @Test
    fun `Second row key should show correct turn degree after clicking turn degree button`() {
        clickTurnDegreeButton()
        keyButtonShouldBe("M2 ")

        clickTurnDegreeButton()
        keyButtonShouldBe("M3 ")

        clickTurnDegreeButton()
        keyButtonShouldBe("M ")
    }

    @Test
    fun `Input method button should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithTag("InputMethodButton")
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Rotation direction button should exist, should be enabled, and have click action`() {
        composeTestRule
            .onNodeWithTag("RotateDirectionButton")
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Turn degree button should exist, should be enabled, and have click action`() {
        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Turn degree button should change to correct number after click`() {
        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .assertTextEquals("1")

        clickTurnDegreeButton()

        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .assertTextEquals("2")

        clickTurnDegreeButton()

        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .assertTextEquals("3")

        clickTurnDegreeButton()

        composeTestRule
            .onNodeWithTag("TurnDegreeButton")
            .assertTextEquals("1")
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
