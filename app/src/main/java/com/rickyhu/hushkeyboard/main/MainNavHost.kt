package com.rickyhu.hushkeyboard.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickyhu.hushkeyboard.home.HomeScreen
import com.rickyhu.hushkeyboard.introduction.IntroductionScreen
import com.rickyhu.hushkeyboard.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object SettingsRoute

@Serializable
object IntroductionRoute

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(
                navigateToIntroduction = { navController.navigate(route = IntroductionRoute) },
                navigateToSettings = { navController.navigate(route = SettingsRoute) }
            )
        }
        composable<SettingsRoute> {
            SettingsScreen()
        }
        composable<IntroductionRoute> {
            IntroductionScreen(
                navigateToHome = { navController.popBackStack() }
            )
        }
    }
}
