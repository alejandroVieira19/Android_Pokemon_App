package com.pokemon_app.domain.repository;

import com.pokemon_app.data.model.PokemonDetailResponse;
import com.pokemon_app.data.model.PokemonResponse;

interface IPokemonRepository {
    // Método para pegar a lista de Pokémons
    suspend fun retrievePokemonList(limit: Int = 151): PokemonResponse


    // Método para pegar os detalhes de um Pokémon específico
    suspend fun retrievePokemonDetails(url: String): PokemonDetailResponse
}

