package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rickyhu.hushkeyboard.home.HomeScreen
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var isSetupKeyboardButtonClicked = false
    private var isSettingsButtonClicked = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(
                navigateToIntroduction = { isSetupKeyboardButtonClicked = true },
                navigateToSettings = { isSettingsButtonClicked = true }
            )
        }
    }

    @Test
    fun `Setup keyboard button should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("Setup Keyboard")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Setup keyboard button action should be invoked upon click`() {
        composeTestRule
            .onNodeWithText("Setup Keyboard")
            .performClick()

        assertTrue(isSetupKeyboardButtonClicked)
    }

    @Test
    fun `Settings button should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("Settings")
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Settings button action should be invoked upon click`() {
        composeTestRule
            .onNodeWithText("Settings")
            .performClick()

        assertTrue(isSettingsButtonClicked)
    }
}
