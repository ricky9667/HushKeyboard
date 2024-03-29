package com.rickyhu.hushkeyboard.ui.home

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun testEnableKeyboardButton() {
        composeTestRule
            .onNodeWithText("Enable keyboard")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }
}
