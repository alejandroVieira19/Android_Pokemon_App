package com.pokemon_app.domain.model.mapper
import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.domain.model.Pokemon

object PokeMapper {
    fun mapToDomain(pokemonDetailResponse: PokemonDetailResponse): Pokemon {

        val types = pokemonDetailResponse.types.map { it.type.name }
        // Criando o objeto Pokemon no domínio
        return Pokemon(
            pokemonId = pokemonDetailResponse.id ?: 1000000,
            pokemonName = pokemonDetailResponse.pokeName,
            pokemonWeight = pokemonDetailResponse.weight ?: 0.0,
            pokemonHeight = pokemonDetailResponse.height ?: 0.0,
            pokemonType = types,
            pokemonDetailImageUrlBackground = pokemonDetailResponse.sprites_images.other?.officialArtwork?.frontDefault ?: "error_url",
            pokemonImageUrlCard = pokemonDetailResponse.sprites_images.other?.home?.frontDefault ?: "error_url"
        )
    }
}
