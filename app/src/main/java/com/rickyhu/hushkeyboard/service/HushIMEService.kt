package com.rickyhu.hushkeyboard.service

import android.view.View
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.rickyhu.hushkeyboard.keyboard.HushKeyboardView
import com.rickyhu.hushkeyboard.keyboard.KeyboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HushIMEService :
    LifecycleInputMethodService(),
    SavedStateRegistryOwner {
    @Inject
    lateinit var viewModel: KeyboardViewModel

    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry = savedStateRegistryController.savedStateRegistry

    override val lifecycle = dispatcher.lifecycle

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
    }
}
