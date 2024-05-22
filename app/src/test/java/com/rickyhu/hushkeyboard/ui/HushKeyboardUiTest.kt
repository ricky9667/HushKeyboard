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
        assertNotationKeyButton("R ")
    }

    @Test
    fun `First row key should show ' notation after clicking counter clockwise button`() {
        clickCounterClockwiseButton()
        assertNotationKeyButton("R' ")

        clickCounterClockwiseButton()
        assertNotationKeyButton("R ")
    }

    @Test
    fun `First row key should show w notation after clicking wide notation button`() {
        clickWideNotationButton()
        assertNotationKeyButton("Rw ")
    }

    @Test
    fun `First row key should show correct turn degree after clicking turn degree button`() {
        clickTurnDegreeButton()
        assertNotationKeyButton("R2 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("R3 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("R ")
    }

    @Test
    fun `Middle turn key should exist, should be enabled, and should have click action`() {
        assertNotationKeyButton("M ")
    }

    @Test
    fun `Middle turn key should show ' notation after clicking counter clockwise button`() {
        clickCounterClockwiseButton()
        assertNotationKeyButton("M' ")

        clickCounterClockwiseButton()
        assertNotationKeyButton("M ")
    }

    @Test
    fun `Middle turn key should show correct turn degree after clicking turn degree button`() {
        clickTurnDegreeButton()
        assertNotationKeyButton("M2 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("M3 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("M ")
    }

    @Test
    fun `Cube rotation key should exist, should be enabled, and should have click action`() {
        assertNotationKeyButton("x ")
    }

    @Test
    fun `Cube rotation key should show ' notation after clicking counter clockwise button`() {
        clickCounterClockwiseButton()
        assertNotationKeyButton("x' ")

        clickCounterClockwiseButton()
        assertNotationKeyButton("x ")
    }

    @Test
    fun `Cube rotation key should show correct turn degree after clicking turn degree button`() {
        clickTurnDegreeButton()
        assertNotationKeyButton("x2 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("x3 ")

        clickTurnDegreeButton()
        assertNotationKeyButton("x ")
    }

    @Test
    fun `Input method button should exist, should be enabled, and should have click action`() {
        assertControlKeyButton("InputMethodButton")
    }

    @Test
    fun `Rotation direction button should exist, should be enabled, and have click action`() {
        assertControlKeyButton("RotateDirectionButton")
    }

    @Test
    fun `Turn degree button should exist, should be enabled, and have click action`() {
        assertControlKeyButton("TurnDegreeButton")
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

    @Test
    fun `Wide notation button should exist, should be enabled, and have click action`() {
        assertControlKeyButton("WideTurnButton")
    }

    @Test
    fun `Delete button should exist, should be enabled, and have click action`() {
        assertControlKeyButton("DeleteButton")
    }

    @Test
    fun `New line button should exist, should be enabled, and have click action`() {
        assertControlKeyButton("NewLineButton")
    }

    private fun assertNotationKeyButton(text: String) {
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun assertControlKeyButton(tag: String) {
        composeTestRule
            .onNodeWithTag(tag)
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
