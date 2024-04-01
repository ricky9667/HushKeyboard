package com.rickyhu.hushkeyboard.service

import android.view.View
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.rickyhu.hushkeyboard.HushKeyboardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HushIMEService : LifecycleInputMethodService(), ViewModelStoreOwner, SavedStateRegistryOwner {

    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry = savedStateRegistryController.savedStateRegistry

    override val viewModelStore = ViewModelStore()

    override val lifecycle = dispatcher.lifecycle

    override fun onCreateInputView(): View {
        val view = HushKeyboardView(this)
        window.window?.decorView.let { decorView ->
            decorView?.setViewTreeLifecycleOwner(this)
            decorView?.setViewTreeViewModelStoreOwner(this)
            decorView?.setViewTreeSavedStateRegistryOwner(this)
        }
        return view
    }

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
    }
}
