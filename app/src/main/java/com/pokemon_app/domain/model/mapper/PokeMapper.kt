package com.pokemon_app.domain.model.mapper

import android.util.Log
import com.pokemon_app.data.model.PokeMoves
import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.domain.model.Pokemon

/**
 * Objeto responsável por realizar a conversão entre modelos de dados de diferentes camadas.
 * A classe `PokeMapper` converte modelos de dados provenientes da camada de rede (API) para o
 * modelo de domínio (Pokemon), e também converte entre o modelo de domínio e o modelo de entidade
 * para armazenamento no banco de dados.
 */
object PokeMapper {

    /**
     * Mapeia a resposta detalhada do Pokémon (da API) para o modelo de domínio.
     *
     * Este método converte a resposta da API (`PokemonDetailResponse`) para um objeto do modelo de domínio (`Pokemon`).
     * Ele extrai informações como o nome, tipo, estatísticas e movimentos do Pokémon para preencher o modelo de domínio.
     *
     * @param pokemonDetailResponse A resposta detalhada do Pokémon proveniente da API.
     * @return Um objeto do modelo de domínio `Pokemon` com os dados extraídos da resposta da API.
     */
    fun mapToDomain(pokemonDetailResponse: PokemonDetailResponse): Pokemon {

        // Extraindo os tipos do Pokémon da resposta da API
        val types = pokemonDetailResponse.types.map { it.type.name }

        // Recuperando a lista de movimentos do Pokémon
        val pokeMoves = retrievePokemonMovesList(pokemonDetailResponse.moves)

        return Pokemon(
            pokemonId = pokemonDetailResponse.id ?: 1000000, // ID do Pokémon (valor padrão caso seja nulo)
            pokemonName = pokemonDetailResponse.pokeName, // Nome do Pokémon
            pokemonWeight = pokemonDetailResponse.weight ?: 0.0, // Peso do Pokémon
            pokemonHeight = pokemonDetailResponse.height ?: 0.0, // Altura do Pokémon
            pokemonType = types, // Tipos do Pokémon
            pokemonDetailImageUrlBackground = pokemonDetailResponse.sprites_images.other?.officialArtwork?.frontDefault ?: "error_url", // URL da imagem de fundo
            pokemonImageUrlCard = pokemonDetailResponse.sprites_images.other?.home?.frontDefault ?: "error_url", // URL da imagem do card
            pokemonMovesList = pokeMoves, // Lista de movimentos do Pokémon
            isPokemonFavorite = false, // Flag de favorito (valor inicial como falso)
            pokemonHP = pokemonDetailResponse.stats.getOrNull(0)?.base_stat ?: 0, // Estatística de HP
            pokemonDefense = pokemonDetailResponse.stats.getOrNull(1)?.base_stat ?: 0, // Estatística de defesa
            pokemonSpeed = pokemonDetailResponse.stats.getOrNull(2)?.base_stat ?: 0, // Estatística de velocidade
            pokemonAttack = pokemonDetailResponse.stats.getOrNull(5)?.base_stat ?: 0 // Estatística de ataque
        )
    }

    /**
     * Mapeia o modelo de domínio (`Pokemon`) para o modelo de entidade de banco de dados (`PokemonEntity`).
     *
     * Este método converte um objeto do modelo de domínio (`Pokemon`) para o modelo de entidade que será
     * armazenado no banco de dados. Ele copia os valores dos atributos do objeto `Pokemon` para o objeto `PokemonEntity`.
     *
     * @param pokemon O objeto `Pokemon` que será convertido.
     * @return O objeto `PokemonEntity` preparado para ser armazenado no banco de dados.
     */
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
            pokemon.pokemonAttack,     // Atributo de ataque
            pokemon.pokemonDefense,    // Atributo de defesa
            pokemon.pokemonHP,         // Atributo de HP
            pokemon.pokemonSpeed       // Atributo de velocidade
        )
    }

    /**
     * Mapeia o modelo de entidade de banco de dados (`PokemonEntity`) para o modelo de domínio (`Pokemon`).
     *
     * Este método converte um objeto do modelo de entidade (`PokemonEntity`) para o modelo de domínio (`Pokemon`).
     * Ele é utilizado para obter os dados do banco de dados e transformá-los em um formato utilizável pela camada de domínio.
     *
     * @param pokemon O objeto `PokemonEntity` que será convertido.
     * @return O objeto `Pokemon` correspondente aos dados do banco de dados.
     */
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
            pokemon.pokemonAttack,      // Atributo de ataque
            pokemon.pokemonDefense,     // Atributo de defesa
            pokemon.pokemonHP,          // Atributo de HP
            pokemon.pokemonSpeed        // Atributo de velocidade
        )
    }

    /**
     * Recupera a lista de movimentos do Pokémon.
     *
     * Este método extrai os movimentos do Pokémon a partir da resposta da API e os retorna em uma lista de strings.
     * Caso algum movimento seja nulo, ele é descartado.
     *
     * @param moves A lista de movimentos do Pokémon fornecida pela resposta da API.
     * @return Uma lista de nomes de movimentos do Pokémon, com os valores nulos removidos.
     */
    private fun retrievePokemonMovesList(moves: List<PokeMoves>): List<String> {
        return moves.mapNotNull { it.move?.name } // Removendo valores nulos
    }
}
