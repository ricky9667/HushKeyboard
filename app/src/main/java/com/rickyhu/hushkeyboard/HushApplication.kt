package com.rickyhu.hushkeyboard

import android.app.Application
import com.rickyhu.hushkeyboard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HushApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HushApplication)
            modules(appModule)
        }
    }
}
