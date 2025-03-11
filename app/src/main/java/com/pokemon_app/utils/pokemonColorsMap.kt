package com.pokemon_app.utils

val colours = mapOf(
    "normal" to "#A8A77A",
    "fire" to "#EE8130",
    "water" to "#6390F0",
    "electric" to "#F7D02C",
    "grass" to "#7AC74C",
    "ice" to "#96D9D6",
    "fighting" to "#C22E28",
    "poison" to "#A33EA1",
    "ground" to "#E2BF65",
    "flying" to "#A98FF3",
    "psychic" to "#F95587",
    "bug" to "#A6B91A",
    "rock" to "#B6A136",
    "ghost" to "#735797",
    "dragon" to "#6F35FC",
    "dark" to "#705746",
    "steel" to "#B7B7CE",
    "fairy" to "#D685AD"
)

fun getColorForPokemonByType(type: String) : String {
    return colours[type] ?: "#777"
}