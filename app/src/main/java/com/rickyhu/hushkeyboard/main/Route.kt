package com.rickyhu.hushkeyboard.main

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object Home : Route

    @Serializable
    object Settings : Route

    @Serializable
    object Introduction : Route
}
