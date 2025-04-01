package com.pokemon_app.presentation.ui.view.composable.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokemon_app.R
import com.pokemon_app.presentation.ui.view.composable.manager.ComposableProvider


abstract class PokemonDetailImage(val pokemonImage: String) : ComposableProvider {
    @Composable
    override fun ProvideComposableContent() {
        PokemonImageDetail(pokemonImage)
    }
}

class PokemonImage(val pokemonImageUrl: String): PokemonDetailImage(pokemonImageUrl) {
}

@Composable
fun PokemonImageDetail(pokemonImageUrl: String) {
    AsyncImage(
        model = pokemonImageUrl,
        contentDescription = stringResource(R.string.content_description),
        modifier = Modifier.fillMaxWidth().
            height(375.dp)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.error)
    )
}

@Preview
@Composable
fun PreviewPokemonImageDetail() {
    PokemonImageDetail("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
}