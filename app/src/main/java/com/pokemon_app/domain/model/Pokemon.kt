package com.pokemon_app.domain.model

import java.io.Serializable


data class Pokemon(
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonHeight: Double,
    val pokemonWeight: Double,
    val pokemonType: List<String?>,
    val pokemonDetailImageUrlBackground: String,
    val pokemonImageUrlCard: String,
    var isPokemonFavorite: Boolean?=false
): Serializable
