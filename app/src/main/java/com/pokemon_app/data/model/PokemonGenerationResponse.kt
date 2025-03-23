package com.pokemon_app.data.model

import com.google.gson.annotations.SerializedName

/**
 * Representa a resposta da geração de Pokémon recebida da API.
 *
 * @param abilities Lista de habilidades do Pokémon.
 * @param id Identificador único do Pokémon na geração.
 * @param main_region A região principal associada ao Pokémon.
 * @param moves Lista de movimentos que o Pokémon pode aprender.
 * @param name Nome do Pokémon na geração.
 * @param names Lista de nomes do Pokémon em diferentes idiomas.
 * @param pokemon_Species Lista de espécies de Pokémon relacionadas a esta geração.
 * @param types Tipos de Pokémon (ex: Fogo, Água, etc.).
 * @param version_groups Lista de grupos de versão (versões do jogo em que o Pokémon aparece).
 */
data class PokemonGenerationResponse(
    @SerializedName("abilities") val abilities: List<PokeNameAndUrl>, // Habilidades do Pokémon
    @SerializedName("id") val id: Int, // Identificador único do Pokémon
    @SerializedName("main_region") val main_region: PokeNameAndUrl, // Região principal do Pokémon
    @SerializedName("moves") val moves: List<PokeNameAndUrl>, // Movimentos que o Pokémon pode aprender
    @SerializedName("name") val name: String, // Nome do Pokémon
    @SerializedName("names") val names: List<PokeNames>, // Nomes do Pokémon em diferentes idiomas
    @SerializedName("pokemon_species") val pokemon_Species: List<PokeNameAndUrl>, // Espécies de Pokémon na geração
    @SerializedName("types") val types: List<PokeTypes?>, // Tipos do Pokémon (ex: Fogo, Água, etc.)
    @SerializedName("Version_groups") val version_groups: List<PokeNameAndUrl> // Grupos de versões do jogo
)

/**
 * Representa o nome de um Pokémon em um idioma específico, usado na listagem de nomes multilíngues.
 *
 * @param language Idioma do nome do Pokémon.
 * @param name Nome do Pokémon no idioma especificado.
 */
data class PokeNames(
    @SerializedName("language") val language: PokeNameAndUrl, // Idioma do nome
    @SerializedName("name") val name: String // Nome do Pokémon
)
