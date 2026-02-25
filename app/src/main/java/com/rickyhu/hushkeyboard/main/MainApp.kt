package com.rickyhu.hushkeyboard.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickyhu.hushkeyboard.data.ThemeOption
import com.rickyhu.hushkeyboard.home.HomeScreen
import com.rickyhu.hushkeyboard.introduction.IntroductionScreen
import com.rickyhu.hushkeyboard.libraries.LibrariesScreen
import com.rickyhu.hushkeyboard.settings.SettingsScreen
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainApp(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.settingsState.collectAsState()

    val isDarkTheme =
        when (state.themeOption) {
            ThemeOption.System -> isSystemInDarkTheme()
            ThemeOption.Light -> false
            ThemeOption.Dark -> true
        }

    HushKeyboardTheme(
        darkTheme = isDarkTheme,
        seedColor = state.keyboardColorOption.color,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Route.Home,
            ) {
                composable<Route.Home> {
                    HomeScreen(
                        navigateToIntroduction = { navController.navigate(route = Route.Introduction) },
                        navigateToSettings = { navController.navigate(route = Route.Settings) },
                    )
                }
                composable<Route.Settings> {
                    SettingsScreen(
                        onLibrariesClick = { navController.navigate(route = Route.Libraries) },
                    )
                }
                composable<Route.Libraries> {
                    LibrariesScreen(
                        onBackClick = { navController.popBackStack() },
                    )
                }
                composable<Route.Introduction> {
                    IntroductionScreen(
                        navigateToHome = { navController.popBackStack() },
                    )
                }
            }
        }
    }
}
