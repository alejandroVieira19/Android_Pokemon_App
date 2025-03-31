package com.pokemon_app.presentation.ui.view.composable.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokemon_app.R
import com.pokemon_app.domain.model.PokemonStatsDTO

@Composable
fun PokemonStatsDetailedView(pokemonStatsDTO: PokemonStatsDTO) {
    Column(modifier = Modifier
        .fillMaxWidth()
       ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        StatRow(
            statText = stringResource(R.string.hp),
            progressBar = pokemonStatsDTO.pokemonHP,
            progressColor = R.color.colorOnline,
            progressValue = pokemonStatsDTO.pokemonHP
        )

        StatRow(
            statText = stringResource(R.string.attack),
            progressBar = pokemonStatsDTO.pokemonAttack,
            progressColor = R.color.stats_attack,
            progressValue = pokemonStatsDTO.pokemonAttack
        )

        StatRow(
            statText = stringResource(R.string.defense),
            progressBar = pokemonStatsDTO.pokemonDefense,
            progressColor = R.color.stats_defense,
            progressValue = pokemonStatsDTO.pokemonDefense
        )

        StatRow(
            statText = stringResource(R.string.speed),
            progressBar = pokemonStatsDTO.pokemonSpeed,
            progressColor = R.color.stats_speed,
            progressValue = pokemonStatsDTO.pokemonSpeed
        )
    }
}

//----------------------------------------------------------------------------------------------------------------------------------------------------

@Composable
fun StatRow(statText: String, progressBar: Int, progressColor: Int, progressValue: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        // Label
        Text(
            text = "$statText:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp)
        )

        // ProgressBar
        LinearProgressIndicator(
            progress = progressBar.toFloat() / 100f,
            modifier = Modifier
                .fillMaxWidth(0.7f) // Ajusta o tamanho do progress
                .padding(16.dp),
            color = colorResource(progressColor),
            trackColor = colorResource(R.color.pikachu_black),
            strokeCap = StrokeCap.Round // Bordas arredondadas
        )

        // Value number
        Text(
            text = "${progressValue}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(end = 20.dp, start = 10.dp)
        )
    }
}


//------------------------------------------------------------------------------------------------------------------------------------------------------

// Preview com fundo diferente
@Preview()
@Composable
fun PreviewPokemonStats() {
    val exampleStats = PokemonStatsDTO(
        pokemonHP = 65,
        pokemonAttack = 75,
        pokemonDefense = 60,
        pokemonSpeed = 80
    )
    Column(
        modifier = Modifier.background(Color.Gray) // Alterando o fundo para cinza
    ) {
        PokemonStatsDetailedView(pokemonStatsDTO = exampleStats)
    }
}

// Preview com fundo customizado
@Preview()
@Composable
fun PreviewPokemonStatsCustomBackground() {
    val exampleStats = PokemonStatsDTO(
        pokemonHP = 90,
        pokemonAttack = 85,
        pokemonDefense = 70,
        pokemonSpeed = 100
    )
    Column(
        modifier = Modifier.background(Color.Gray) // Fundo transparente
    ) {
        PokemonStatsDetailedView(pokemonStatsDTO = exampleStats)
    }
}

// Preview com fundo de cor sólida
@Preview()
@Composable
fun PreviewPokemonStatsSolidBackground() {
    val exampleStats = PokemonStatsDTO(
        pokemonHP = 50,
        pokemonAttack = 60,
        pokemonDefense = 40,
        pokemonSpeed = 55
    )
    Column(
        modifier = Modifier.background(Color.Gray) // Fundo transparente
    ) {
        PokemonStatsDetailedView(pokemonStatsDTO = exampleStats)
    }
}

// Preview com fundo transparente
@Preview(showBackground = true, backgroundColor = 0xFFFF0000)
@Composable
fun PreviewPokemonStatsTransparent() {
    val exampleStats = PokemonStatsDTO(
        pokemonHP = 100,
        pokemonAttack = 95,
        pokemonDefense = 90,
        pokemonSpeed = 120
    )
    PokemonStatsDetailedView(pokemonStatsDTO = exampleStats)

}
