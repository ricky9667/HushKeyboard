package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.rickyhu.hushkeyboard.introduction.Step1Section
import com.rickyhu.hushkeyboard.introduction.Step2Section
import com.rickyhu.hushkeyboard.introduction.Step3Section
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
    private var isSetupInputMethodButtonClicked = false

    @Test
    fun `Enable HUSH Keyboard button should be filled when isSectionFinished is false`() {
        composeTestRule.setContent {
            Step1Section(
                isSectionFinished = false,
                onSectionButtonClicked = {},
            )
        }

        composeTestRule
            .onNodeWithTag("EnableKeyboardButtonFilled")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Enable HUSH Keyboard button should be outlined when isSectionFinished is true`() {
        composeTestRule.setContent {
            Step1Section(
                isSectionFinished = true,
                onSectionButtonClicked = {},
            )
        }

        composeTestRule
            .onNodeWithTag("EnableKeyboardButtonOutlined")
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
                },
            )
        }

        composeTestRule
            .onNodeWithTag("EnableKeyboardButtonFilled")
            .performClick()

        assertTrue(isEnableKeyboardButtonClicked)
    }

    @Test
    fun `Select Input Method button should be filled when isSectionFinished is false`() {
        composeTestRule.setContent {
            Step2Section(
                isSectionFinished = false,
                onSectionButtonClicked = {},
            )
        }

        composeTestRule
            .onNodeWithTag("SelectInputMethodButtonFilled")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Select Input Method button should be outlined when isSectionFinished is true`() {
        composeTestRule.setContent {
            Step2Section(
                isSectionFinished = true,
                onSectionButtonClicked = {},
            )
        }

        composeTestRule
            .onNodeWithTag("SelectInputMethodButtonOutlined")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Select Input Method button should invoke click action`() {
        composeTestRule.setContent {
            Step2Section(
                isSectionFinished = isSetupInputMethodButtonClicked,
                onSectionButtonClicked = {
                    isSetupInputMethodButtonClicked = true
                },
            )
        }

        composeTestRule
            .onNodeWithTag("SelectInputMethodButtonFilled")
            .performClick()

        assertTrue(isSetupInputMethodButtonClicked)
    }

    @Test
    fun `Section 3 TextField should be displayed and should be enabled to enter text`() {
        composeTestRule.setContent {
            Step3Section(
                navigateToHome = {},
            )
        }

        composeTestRule
            .onNodeWithText("Type here")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performTextInput("Test input")

        composeTestRule
            .onNodeWithText("Test input")
            .assertIsDisplayed()
    }
}
