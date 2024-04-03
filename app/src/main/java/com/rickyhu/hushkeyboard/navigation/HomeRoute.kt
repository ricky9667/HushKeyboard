package com.rickyhu.hushkeyboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rickyhu.hushkeyboard.home.HomeScreen

const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(onSettingsClick: () -> Unit) {
    composable(route = homeRoute) {
        HomeScreen(onSettingsClick)
    }
}
