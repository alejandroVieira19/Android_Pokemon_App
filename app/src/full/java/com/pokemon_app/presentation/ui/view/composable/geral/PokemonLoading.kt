package com.pokemon_app.presentation.ui.view.composable.geral

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokemon_app.R
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailDrawableImage


data class PokemonLoadingDTO(
    val text: String,
    val image: Int ? = null
)

@Composable
fun LoadingPokemonView(pokemonLoadingDTO: PokemonLoadingDTO) {
    Column (modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.blur)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        pokemonLoadingDTO.image?.let { PokemonDetailDrawableImage(it, 80, 80) }
        Text(
            text = pokemonLoadingDTO.text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp)
            )
    }
}


@Composable
@Preview
fun LoadingPokemonViewPreview() {
    LoadingPokemonView(PokemonLoadingDTO(
        stringResource(R.string.no_pokemons_found),
        R.drawable.pokeball,
    ))
}