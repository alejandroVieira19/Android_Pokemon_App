package com.pokemon_app.data.model

import com.google.gson.annotations.SerializedName

/**
 * Classe de dados para mapear a resposta da API que contém informações sobre os 151 primeiros Pokémon.
 *
 * @param count Quantidade total de Pokémon disponíveis na resposta da API.
 * @param next URL para a próxima página de resultados, caso haja mais Pokémon.
 * @param previous URL para a página anterior de resultados, se existir. Pode ser nulo caso não haja página anterior.
 * @param results Lista de objetos contendo o nome e a URL de cada Pokémon na resposta.
 */
data class PokemonResponse(
    @SerializedName("count") val count: Int, // Quantidade total de Pokémon
    @SerializedName("next") val next: String, // URL para a próxima página de resultados
    @SerializedName("previous") val previous: String? = null, // URL para a página anterior (pode ser nula)
    @SerializedName("results") val results: List<PokeNameAndUrl> // Lista de Pokémon com nome e URL
)
