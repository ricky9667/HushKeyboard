package com.rickyhu.hushkeyboard.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.navigation.AppNavHost
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun MainApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.settingsState.collectAsState()

    val isDarkTheme = when (state.themeOption) {
        ThemeOption.System -> isSystemInDarkTheme()
        ThemeOption.Light -> false
        ThemeOption.Dark -> true
    }

    HushKeyboardTheme(darkTheme = isDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost()
        }
    }
}
