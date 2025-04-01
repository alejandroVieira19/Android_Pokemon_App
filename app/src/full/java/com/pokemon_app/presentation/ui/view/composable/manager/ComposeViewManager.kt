package com.pokemon_app.presentation.ui.view.composable.manager

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

object ComposeViewManager {

    fun setComposableContent(
        composeView: ComposeView,
        content: ComposableProvider,
    ) {
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                content.ProvideComposableContent()
            }
        }
    }
}

fun setComposableContentForKotlin(
    composeView: ComposeView,
    content: @Composable ComposableProvider.() -> Unit,
) {
    composeView.apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ComposableProviderImpl().content()
        }
    }
}

interface ComposableProvider {
    @Composable
    fun ProvideComposableContent()
}

private class ComposableProviderImpl : ComposableProvider {
    @Composable
    override fun ProvideComposableContent() {
    }
}