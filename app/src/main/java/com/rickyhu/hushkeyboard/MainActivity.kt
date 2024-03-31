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
import com.rickyhu.hushkeyboard.main.MainViewModel
import com.rickyhu.hushkeyboard.navigation.AppNavHost
import com.rickyhu.hushkeyboard.settings.options.ThemeOption
import com.rickyhu.hushkeyboard.ui.theme.HushKeyboardTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
    }
}
