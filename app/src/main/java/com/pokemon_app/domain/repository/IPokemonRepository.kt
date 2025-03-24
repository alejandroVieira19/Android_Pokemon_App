package com.pokemon_app.domain.repository

import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.data.model.PokemonGenerationResponse
import com.pokemon_app.data.model.PokemonResponse

/**
 * Interface que define as operações para acessar dados relacionados a Pokémon.
 *
 * Esta interface especifica os métodos necessários para obter informações sobre Pokémon,
 * como a lista de Pokémons, detalhes de um Pokémon específico, Pokémons por geração e informações
 * detalhadas de um Pokémon pelo nome. Ela abstrai a lógica de obtenção de dados e é implementada
 * para acessar fontes de dados como APIs, banco de dados, etc.
 */
interface IPokemonRepository {

    /**
     * Recupera uma lista de Pokémons.
     *
     * @param limit O número de Pokémons a ser retornado. O valor padrão é 151.
     * @return Um objeto [PokemonResponse] contendo a lista de Pokémons.
     */
    suspend fun retrievePokemonList(limit: Int = 151): PokemonResponse

    /**
     * Recupera os detalhes de um Pokémon específico a partir de sua URL.
     *
     * @param url A URL do Pokémon cujos detalhes devem ser recuperados.
     * @return Um objeto [PokemonDetailResponse] contendo os detalhes do Pokémon.
     */
    suspend fun retrievePokemonDetails(url: String): PokemonDetailResponse

    /**
     * Recupera os Pokémons de uma geração específica.
     *
     * @param id O ID da geração para a qual os Pokémons devem ser recuperados.
     * @return Um objeto [PokemonGenerationResponse] contendo os Pokémons da geração.
     */
    suspend fun retrievePokemonsByGeneration(id: Int): PokemonGenerationResponse

    /**
     * Recupera os detalhes de um Pokémon pelo seu nome.
     *
     * @param name O nome do Pokémon cujos detalhes devem ser recuperados.
     * @return Um objeto [PokemonDetailResponse] contendo os detalhes do Pokémon.
     */
    suspend fun retrievePokemonByName(name: String): PokemonDetailResponse
}
