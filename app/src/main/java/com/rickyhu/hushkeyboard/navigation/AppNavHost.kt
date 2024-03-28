package com.rickyhu.hushkeyboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = homeRoute) {
        homeScreen(onSettingsClick = { navController.navigateToSettings() })
        settingsScreen()
    }
}
