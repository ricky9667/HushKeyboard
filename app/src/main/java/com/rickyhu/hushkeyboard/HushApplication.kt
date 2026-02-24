package com.rickyhu.hushkeyboard

import android.app.Application
import com.rickyhu.hushkeyboard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class HushApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (GlobalContext.getOrNull() != null) stopKoin()
        startKoin {
            androidContext(this@HushApplication)
            modules(appModule)
        }
    }
}
