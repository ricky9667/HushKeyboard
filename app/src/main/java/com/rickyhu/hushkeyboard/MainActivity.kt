package com.rickyhu.hushkeyboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.rickyhu.hushkeyboard.settings.AppSettings
import com.rickyhu.hushkeyboard.settings.dataStore
import com.rickyhu.hushkeyboard.ui.NavGraphs
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsState by applicationContext.dataStore.data.collectAsState(
                initial = AppSettings()
            )

            HushKeyboardTheme(
                darkTheme = settingsState.themeOption.isDarkTheme(
                    isSystemInDarkMode = isSystemInDarkTheme()
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}
