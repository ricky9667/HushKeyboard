package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rickyhu.hushkeyboard.settings.SettingsContent
import com.rickyhu.hushkeyboard.settings.SettingsState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SettingsScreenUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsState(),
                onThemeSelected = {},
                onWideNotationOptionSelected = {},
                onAddSpaceBetweenNotationChanged = {},
                onVibrateOnTapChanged = {}
            )
        }
    }

    @Test
    fun `App theme dropdown item should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("App Theme")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `AppThemeDropdownMenu should display after AppThemeDropdownItem is clicked`() {
        composeTestRule
            .onNodeWithText("App Theme")
            .performClick()

        composeTestRule
            .onNodeWithTag("ThemeOptionDropdownMenu")
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun `All AppThemeDropdownMenuItems should display after AppThemeDropdownItem is clicked`() {
        composeTestRule
            .onNodeWithText("App Theme")
            .performClick()

        composeTestRule
            .onAllNodesWithTag("ThemeOptionDropdownMenuItem")
            .assertAll(isEnabled())
    }

    @Test
    fun `Wide notation item should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("Wide notation")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun `WideNotationDropdownMenu should display after WideNotationDropdownItem is clicked`() {
        composeTestRule
            .onNodeWithText("Wide notation")
            .performClick()

        composeTestRule
            .onNodeWithTag("WideNotationOptionDropdownMenu")
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun `All WideNotationDropdownMenuItems should display after WideNotationDropdown is clicked`() {
        composeTestRule
            .onNodeWithText("Wide notation")
            .performClick()

        composeTestRule
            .onAllNodesWithTag("WideNotationOptionDropdownMenuItem")
            .assertAll(isEnabled())
    }

    @Test
    fun `Add space after notation should exist, should be enabled, and should have click action`() {
        composeTestRule
            .onNodeWithText("Add space after notation")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }
}
