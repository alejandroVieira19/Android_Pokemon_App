package com.pokemon_app.data.network

import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApiService {
    // Endpoint para pegar a lista de Pokémons da 1ª geração (limitando a 151)
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 151): PokemonResponse

    // Endpoint para pegar os detalhes de um Pokémon específico pelo ID
    @GET("pokemon/{idOrName}")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonDetailResponse

    @GET()
    suspend fun getPokemonByUrl(@Url url: String): PokemonDetailResponse
}