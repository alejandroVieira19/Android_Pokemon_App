package com.pokemon_app.domain.model.mapper

import android.util.Log
import com.pokemon_app.data.model.PokeMoves
import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.database.PokemonEntity
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
            isPokemonFavorite = false,
            pokemonHP = pokemonDetailResponse.stats.getOrNull(0)?.base_stat ?: 0,
            pokemonDefense = pokemonDetailResponse.stats.getOrNull(1)?.base_stat ?: 0,
            pokemonSpeed = pokemonDetailResponse.stats.getOrNull(2)?.base_stat ?: 0,
            pokemonAttack = pokemonDetailResponse.stats.getOrNull(5)?.base_stat ?: 0
            )
    }

    fun mapFromDomainToEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            pokemon.pokemonId,
            pokemon.pokemonName,
            pokemon.pokemonHeight,
            pokemon.pokemonWeight,
            pokemon.pokemonType,
            pokemon.pokemonDetailImageUrlBackground,
            pokemon.pokemonImageUrlCard,
            pokemon.pokemonMovesList,
            pokemon.isPokemonFavorite,
            pokemon.pokemonAttack,     // Adicionando os novos atributos
            pokemon.pokemonDefense,
            pokemon.pokemonHP,
            pokemon.pokemonSpeed
        )
    }

    fun mapFromEntityToDomain(pokemon: PokemonEntity): Pokemon {
        return Pokemon(
            pokemon.pokemonId,
            pokemon.pokemonName,
            pokemon.pokemonHeight,
            pokemon.pokemonWeight,
            pokemon.pokemonType,
            pokemon.pokemonDetailImageUrlBackground,
            pokemon.pokemonImageUrlCard,
            pokemon.pokemonMovesList,
            pokemon.isPokemonFavorite,
            pokemon.pokemonAttack,      // Adicionando os novos atributos
            pokemon.pokemonDefense,
            pokemon.pokemonHP,
            pokemon.pokemonSpeed
        )
    }


    private fun retrievePokemonMovesList(moves: List<PokeMoves>): List<String> {
        return moves.mapNotNull { it.move?.name } // Removendo nulos
    }
}
