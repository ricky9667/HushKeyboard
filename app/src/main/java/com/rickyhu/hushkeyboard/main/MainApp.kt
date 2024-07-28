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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.home.HomeScreen
import com.rickyhu.hushkeyboard.settings.SettingsScreen
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import kotlinx.serialization.Serializable

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
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = HomeRoute
            ) {
                composable<HomeRoute> {
                    HomeScreen(
                        onSettingsClick = { navController.navigate(route = SettingsRoute) }
                    )
                }
                composable<SettingsRoute> {
                    SettingsScreen()
                }
            }
        }
    }
}

@Serializable
object HomeRoute

@Serializable
object SettingsRoute
