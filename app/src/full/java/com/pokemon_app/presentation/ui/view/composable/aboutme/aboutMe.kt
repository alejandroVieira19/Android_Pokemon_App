package com.pokemon_app.presentation.ui.view.composable.aboutme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokemon_app.R
import com.pokemon_app.domain.model.PokemonAboutMeDTO
import com.pokemon_app.domain.model.PokemonStatsDTO
import com.pokemon_app.presentation.ui.view.composable.stats.PokemonStatsDetailedView

@Composable
fun PokemonAboutMeDetailedView(pokemonAboutMeDto: PokemonAboutMeDTO) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {  }

    AboutMeRow(
        aboutMeText = R.string.weight,
        pokemonAboutMeValue = pokemonAboutMeDto.pokemonWeight
    )

    AboutMeRow(
        aboutMeText = R.string.height,
        pokemonAboutMeValue = pokemonAboutMeDto.pokemonHeight
    )

    AboutMeRow(
        aboutMeText = R.string.type,
        pokemonAboutMeValue = pokemonAboutMeDto.pokemonTypeConcatenate
    )

    AboutMePokemonTypesImage(
        pokemonTypeImage = pokemonAboutMeDto.pokemonTypesImage
    )


}

@Composable
fun AboutMePokemonTypesImage(pokemonTypeImage: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 45.dp)
            .height(19.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        pokemonTypeImage.forEach { imageRes ->
            androidx.compose.foundation.Image(
                painter = androidx.compose.ui.res.painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(42.dp)
            )
        }
    }
}


@Composable
fun AboutMeRow(aboutMeText: Int, pokemonAboutMeValue: String) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

        // aboutMeText
        Text(
            text = stringResource(aboutMeText),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp)
        )

        // pokemonAboutMeValue
        Text(
            text = "${pokemonAboutMeValue}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Preview com fundo diferente
@Preview()
@Composable
fun PreviewPokemonStats() {
    val exampleStats = PokemonAboutMeDTO(
 pokemonHeight = "300",
     pokemonWeight = "20",
     pokemonTypeConcatenate = "fire/flying",
     pokemonTypesImage = listOf(R.drawable.battrio_fire_type, R.drawable.battrio_flying_type)
    )
    Column(
        modifier = Modifier.background(Color.Gray) // Alterando o fundo para cinza
    ) {
        PokemonAboutMeDetailedView(pokemonAboutMeDto = exampleStats)
    }
}