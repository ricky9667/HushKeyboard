package com.rickyhu.hushkeyboard.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
}
