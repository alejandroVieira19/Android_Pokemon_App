package com.pokemon_app.data.network

import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    // Endpoint para pegar a lista de Pokémons da 1ª geração (limitando a 151)
    @GET("pokemon")
     fun getPokemonList(@Query("limit") limit: Int? = 151): Call<PokemonResponse>

    // Endpoint para pegar os detalhes de um Pokémon específico pelo ID
    @GET("pokemon/{idOrName}")
    fun getPokemonDetails(@Path("id") id: Int): Call<PokemonDetailResponse>
}