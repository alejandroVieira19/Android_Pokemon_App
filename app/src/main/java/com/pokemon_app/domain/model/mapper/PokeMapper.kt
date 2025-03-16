package com.pokemon_app.domain.model.mapper

import android.util.Log
import com.pokemon_app.data.model.PokeMoves
import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.domain.model.Pokemon

object PokeMapper {
    fun mapToDomain(pokemonDetailResponse: PokemonDetailResponse): Pokemon {

        val types = pokemonDetailResponse.types.map { it.type.name }
        val pokeMoves = retrievePokemonMovesList(pokemonDetailResponse.moves)

        return Pokemon(
            pokemonId = pokemonDetailResponse.id ?: 1000000, // Confirme se é Int? ou Int
            pokemonName = pokemonDetailResponse.pokeName, // Verifique o nome correto
            pokemonWeight = pokemonDetailResponse.weight ?: 0.0,
            pokemonHeight = pokemonDetailResponse.height ?: 0.0,
            pokemonType = types,
            pokemonDetailImageUrlBackground = pokemonDetailResponse.sprites_images.other?.officialArtwork?.frontDefault ?: "error_url",
            pokemonImageUrlCard = pokemonDetailResponse.sprites_images.other?.home?.frontDefault ?: "error_url",
            pokemonMovesList = pokeMoves,
            isPokemonFavorite = false
        )
    }

    private fun retrievePokemonMovesList(moves: List<PokeMoves>): List<String> {
        return moves.mapNotNull { it.move?.name } // Removendo nulos
    }
}
