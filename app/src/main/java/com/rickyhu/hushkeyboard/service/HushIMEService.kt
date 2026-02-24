package com.rickyhu.hushkeyboard.service

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.rickyhu.hushkeyboard.data.SettingsRepository
import com.rickyhu.hushkeyboard.keyboard.HushKeyboardView
import com.rickyhu.hushkeyboard.keyboard.KeyboardViewModel
import com.rickyhu.hushkeyboard.keyboard.KeyboardViewModelFactory
import org.koin.android.ext.android.inject

class HushIMEService :
    LifecycleInputMethodService(),
    SavedStateRegistryOwner,
    ViewModelStoreOwner {
    private val settingsRepository: SettingsRepository by inject()
    lateinit var viewModel: KeyboardViewModel

    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry = savedStateRegistryController.savedStateRegistry

    override val lifecycle = dispatcher.lifecycle

    private val _viewModelStore = ViewModelStore()
    override val viewModelStore: ViewModelStore get() = _viewModelStore

    override fun onCreateInputView(): View {
        val view = HushKeyboardView(this)
        window.window?.decorView.let { decorView ->
            decorView?.setViewTreeLifecycleOwner(this)
            decorView?.setViewTreeSavedStateRegistryOwner(this)
        }
        return view
    }

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
        viewModel =
            ViewModelProvider(
                owner = this,
                factory = KeyboardViewModelFactory(settingsRepository),
            )[KeyboardViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewModelStore.clear()
    }
}
