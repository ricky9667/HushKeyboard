package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen()
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
}
