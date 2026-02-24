package com.rickyhu.hushkeyboard.di

import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.keyboard.KeyboardViewModel
import com.rickyhu.hushkeyboard.main.MainViewModel
import com.rickyhu.hushkeyboard.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        single { SettingsRepository(androidContext()) }
        viewModel { MainViewModel(get()) }
        viewModel { SettingsViewModel(get()) }
        viewModel { KeyboardViewModel(get()) }
    }
