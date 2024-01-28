package com.rickyhu.hushkeyboard.settings

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val systemTheme: SystemTheme = SystemTheme.SYSTEM
)

enum class SystemTheme {
    LIGHT, DARK, SYSTEM
}
