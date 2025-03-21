package com.pokemon_app.data.model

import com.google.gson.annotations.SerializedName

data class PokemonGenerationResponse(
    @SerializedName("abilities") val abilities: List<PokeNameAndUrl>,
    @SerializedName("id") val id :Int,
    @SerializedName("main_region") val main_region : PokeNameAndUrl,
    @SerializedName("moves") val moves : List<PokeNameAndUrl>,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<PokeNames>,
    @SerializedName("pokemon_species") val pokemon_Species : List<PokeNameAndUrl>,
    @SerializedName("types") val types: List<PokeTypes?>,
    @SerializedName("Version_groups") val version_groups: List<PokeNameAndUrl>

)


data class PokeNames(
     @SerializedName("language") val language : PokeNameAndUrl,
     @SerializedName("name") val name: String
)
