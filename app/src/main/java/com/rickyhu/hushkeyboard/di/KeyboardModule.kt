package com.rickyhu.hushkeyboard.di

import android.content.Context
import android.view.inputmethod.InputConnection
import com.rickyhu.hushkeyboard.service.HushIMEService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class KeyboardModule {
    @Provides
    fun provideInputConnection(
        @ApplicationContext context: Context
    ): InputConnection = (context as HushIMEService).currentInputConnection
}
