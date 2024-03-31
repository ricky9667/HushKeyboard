package com.rickyhu.hushkeyboard.data

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class SettingsRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    val settings = context.dataStore.data
}
