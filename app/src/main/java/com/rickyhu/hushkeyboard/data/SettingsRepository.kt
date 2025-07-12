package com.rickyhu.hushkeyboard.data

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class SettingsRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private val dataStore = context.dataStore
    val settings = dataStore.data

    suspend fun updateThemeOption(themeOption: ThemeOption) {
        dataStore.updateData { it.copy(themeOption = themeOption) }
    }

    suspend fun updateWideNotationOption(wideNotationOption: WideNotationOption) {
        dataStore.updateData { it.copy(wideNotationOption = wideNotationOption) }
    }

    suspend fun updateSmartDelete(smartDelete: Boolean) {
        dataStore.updateData { it.copy(smartDelete = smartDelete) }
    }

    suspend fun updateAddSpaceBetweenNotation(addSpaceBetweenNotation: Boolean) {
        dataStore.updateData { it.copy(addSpaceAfterNotation = addSpaceBetweenNotation) }
    }

    suspend fun updateVibrateOnTap(vibrateOnTap: Boolean) {
        dataStore.updateData { it.copy(vibrateOnTap = vibrateOnTap) }
    }
}
