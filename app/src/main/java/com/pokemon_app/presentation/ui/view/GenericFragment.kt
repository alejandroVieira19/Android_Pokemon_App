package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.pokemon_app.presentation.ui.view.composable.manager.ComposableProvider
import com.pokemon_app.presentation.ui.view.composable.manager.ComposeViewManager

abstract class GenericFragment : Fragment() {
    open fun onFragmentDataReceive(data:Bundle) {}

    protected fun setComposableContent(composeView: ComposeView, classToUse: ComposableProvider) {
        ComposeViewManager.setComposableContent(
            composeView,
            classToUse
        )
    }
}