package com.pokemon_app.data.model

import com.google.gson.annotations.SerializedName

// Data class para mapear a resposta da API que contém os 151 primeiros Pokémon
data class PokemonResponse(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String?=null,
    @SerializedName("results") val results: List<PokeNameAndUrl>
)




