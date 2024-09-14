package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.rickyhu.hushkeyboard.introduction.Step1Section
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntroductionScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var isEnableKeyboardButtonClicked = false

    @Test
    fun `Enable HUSH Keyboard button should be filled when isSectionFinished is false`() {
        composeTestRule.setContent {
            Step1Section(
                isSectionFinished = false,
                onSectionButtonClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag("SetupKeyboardButtonFilled")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Enable HUSH Keyboard button should be outlined when isSectionFinished is true`() {
        composeTestRule.setContent {
            Step1Section(
                isSectionFinished = true,
                onSectionButtonClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag("SetupKeyboardButtonOutlined")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Enable HUSH Keyboard button should invoke click action`() {
        composeTestRule.setContent {
            Step1Section(
                isSectionFinished = isEnableKeyboardButtonClicked,
                onSectionButtonClicked = {
                    isEnableKeyboardButtonClicked = true
                }
            )
        }

        composeTestRule
            .onNodeWithTag("SetupKeyboardButtonFilled")
            .performClick()

        assertTrue(isEnableKeyboardButtonClicked)
    }
}
