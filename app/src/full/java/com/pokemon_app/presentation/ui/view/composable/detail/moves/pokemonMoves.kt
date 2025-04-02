package com.pokemon_app.presentation.ui.view.composable.detail.moves

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokemon_app.R


// TODO ---> ESTOU A TER BUG !!!
@Composable
fun PokemonMovesView(pokeMoves: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            ,

        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokeMoves.forEach{ move ->
            MoveItem(move)
        }
    }
}

@Composable
fun MoveItem(move: String) {
    Text(
        text = move,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            //.background(colorResource(R.color.blur), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonMovesDetailedView() {
    val sampleMoves = listOf("Thunderbolt", "Flamethrower", "Hydro Pump", "Earthquake", "Shadow Ball")
    PokemonMovesView(pokeMoves = sampleMoves)
}