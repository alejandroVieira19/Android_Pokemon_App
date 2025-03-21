package com.pokemon_app.domain.model

import com.google.gson.Gson
import com.pokemon_app.interactions.GenericStates
import java.io.Serializable


data class Pokemon(
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonHeight: Double,
    val pokemonWeight: Double,
    val pokemonType: List<String?>,
    val pokemonDetailImageUrlBackground: String,
    val pokemonImageUrlCard: String,
    val pokemonMovesList : List<String>,
    var isPokemonFavorite: Boolean,
    val pokemonAttack: Int,
    val pokemonDefense:Int,
    val pokemonHP: Int,
    val pokemonSpeed:Int
): Serializable


