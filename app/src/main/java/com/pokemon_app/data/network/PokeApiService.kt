package com.pokemon_app.data.network

import com.pokemon_app.data.model.PokemonDetailResponse
import com.pokemon_app.data.model.PokemonGenerationResponse
import com.pokemon_app.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Interface para interagir com a API de Pokémons utilizando o Retrofit.
 * Contém os endpoints para obter informações sobre Pokémons e gerações.
 */
interface PokeApiService {

    /**
     * Endpoint para pegar a lista de Pokémons, limitando o número de resultados.
     *
     * @param limit O número de Pokémons a ser retornado. Por padrão, é 151 (correspondente à 1ª geração).
     * @return Uma resposta do tipo [PokemonResponse] com a lista de Pokémons.
     */
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 151): PokemonResponse

    /**
     * Endpoint para pegar os detalhes de um Pokémon específico pelo seu ID.
     *
     * @param id O ID do Pokémon a ser pesquisado.
     * @return Uma resposta do tipo [PokemonDetailResponse] com os detalhes do Pokémon.
     */
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: String): PokemonDetailResponse

    /**
     * Endpoint para pegar os detalhes de um Pokémon a partir de uma URL fornecida.
     *
     * @param url A URL do Pokémon que será utilizada para fazer a requisição.
     * @return Uma resposta do tipo [PokemonDetailResponse] com os detalhes do Pokémon.
     */
    @GET()
    suspend fun getPokemonByUrl(@Url url: String): PokemonDetailResponse

    /**
     * Endpoint para pegar os Pokémons de uma geração específica.
     *
     * @param id O ID da geração (ex: 1 para a 1ª geração).
     * @return Uma resposta do tipo [PokemonGenerationResponse] com os Pokémons da geração solicitada.
     */
    @GET("generation/{id}")
    suspend fun getPokemonByGeneration(@Path("id") id: Int): PokemonGenerationResponse
}
