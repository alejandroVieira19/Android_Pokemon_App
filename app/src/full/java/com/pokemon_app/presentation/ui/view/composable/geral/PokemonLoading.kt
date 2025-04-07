package com.pokemon_app.presentation.ui.view.composable.geral

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pokemon_app.R
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailDrawableImage


data class PokemonLoadingDTO(
    val text: String,
    val animationSize: Int,
    val animationImage: Int ? = R.raw.pokeball_animation,
    )

@Composable
fun LoadingPokemonView(pokemonLoadingDTO: PokemonLoadingDTO) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blur)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            pokemonLoadingDTO.animationImage?.let { LottieAnimationView(it, pokemonLoadingDTO.animationSize) }

            // Texto abaixo da animação
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
}

@Composable
fun LottieAnimationView(animation:Int, animationSize: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever // Loop infinito
    )
    // Animação Lottie
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
            .size(animationSize.dp) // Aumentei o tamanho para melhor visibilidade
    )
}



@Composable
@Preview
fun LoadingPokemonViewPreview() {
    LoadingPokemonView(PokemonLoadingDTO(
        stringResource(R.string.no_pokemons_found),
        80,
        R.raw.pokeball_animation,

    ))
}