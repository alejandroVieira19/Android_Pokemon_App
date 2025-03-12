package com.pokemon_app.domain.model

data class Pokemon(
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonHeight: Double,
    val pokemonWeight: Double,
    val pokemonType: List<String?>,
    val pokemonDetailImageUrlBackground: String,
    val pokemonImageUrlCard: String,
    var isPokemonFavorite: Boolean?=false
){
    fun favoritePokemon() {
        isPokemonFavorite = true;
    }
}
