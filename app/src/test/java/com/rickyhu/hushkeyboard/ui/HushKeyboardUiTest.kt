package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rickyhu.hushkeyboard.keyboard.HushKeyboardContent
import com.rickyhu.hushkeyboard.keyboard.KeyboardState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HushKeyboardUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `First row key should exist, should be enabled, and should have click action`() {
        composeTestRule.setContent {
            HushKeyboardContent(state = KeyboardState())
        }

        composeTestRule
            .onNodeWithText("R ")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
    }
}
