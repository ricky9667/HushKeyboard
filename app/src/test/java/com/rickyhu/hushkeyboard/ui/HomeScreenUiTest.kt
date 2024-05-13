package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.rickyhu.hushkeyboard.home.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    var clicked = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(onSettingsClick = { clicked = true })
        }
    }

    @Test
    fun `Enable keyboard button should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("Enable keyboard")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `Select input button should exist, should be enable, and should have click action`() {
        composeTestRule
            .onNodeWithText("Select input method")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `The textField should be displayed and should be enabled to enter text`() {
        composeTestRule
            .onNodeWithText("Type here")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performTextInput("Test input")

        composeTestRule
            .onNodeWithText("Test input")
            .assertIsDisplayed()
    }

    @Test
    fun `Settings button should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithContentDescription("Settings")
            .assertExists()
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Settings button should disappear and action should be invoked upon click`() {
        composeTestRule
            .onNodeWithContentDescription("Settings")
            .performClick()

        composeTestRule
            .onNodeWithText("Settings")
            .assertIsNotDisplayed()

        assert(clicked)
    }
}
