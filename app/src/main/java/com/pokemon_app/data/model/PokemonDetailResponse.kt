package com.pokemon_app.data.model

import androidx.resourceinspection.annotation.Attribute.IntMap
import com.google.gson.annotations.SerializedName

/**
 * Representa a resposta detalhada de um Pokémon com várias informações.
 *
 * @param abilities Lista de habilidades do Pokémon.
 * @param base_experience A experiência base do Pokémon.
 * @param cries Detalhes sobre os gritos do Pokémon.
 * @param forms Lista de formas do Pokémon.
 * @param game_indices Índices de jogos em que o Pokémon aparece.
 * @param height Altura do Pokémon em decímetros.
 * @param heldItems Lista de itens que o Pokémon pode segurar.
 * @param id Identificador único do Pokémon.
 * @param is_default Indica se o Pokémon é o padrão em sua espécie.
 * @param location_area_encounters Detalhes sobre as áreas onde o Pokémon pode ser encontrado.
 * @param moves Lista de movimentos que o Pokémon pode aprender.
 * @param pokeName Nome do Pokémon.
 * @param order Ordem do Pokémon na Pokédex.
 * @param pastAbilities Lista de habilidades do Pokémon em gerações passadas.
 * @param pastTypes Lista de tipos do Pokémon em gerações passadas.
 * @param species Informações sobre a espécie do Pokémon.
 * @param sprites_images Sprites de imagens do Pokémon.
 * @param stats Estatísticas do Pokémon.
 * @param types Tipos do Pokémon.
 * @param weight Peso do Pokémon em hectogramas.
 */
data class PokemonDetailResponse(
    @SerializedName("abilities") val abilities: List<PokeAbilities>,
    @SerializedName("base_experience") val base_experience: Int?,
    @SerializedName("cries") val cries: PokeCries,
    @SerializedName("forms") val forms: List<PokeNameAndUrl>,
    @SerializedName("game_indices") val game_indices: List<PokeGameIndices>,
    @SerializedName("height") val height: Int?,
    @SerializedName("held_items") val heldItems: List<PokeHeldItem>,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_default") val is_default: Boolean?,
    @SerializedName("location_area_encounters") val location_area_encounters: String,
    @SerializedName("moves") val moves: List<PokeMoves>,
    @SerializedName("name") val pokeName: String,
    @SerializedName("order") val order: Int?,
    @SerializedName("past_abilities") val pastAbilities: List<PokeAbilities>,
    @SerializedName("past_types") val pastTypes: List<PokeTypes>,
    @SerializedName("species") val species: PokeNameAndUrl,
    @SerializedName("sprites") val sprites_images: PokeSprites,
    @SerializedName("stats") val stats: List<PokeStats>,
    @SerializedName("types") val types: List<PokeTypes>,
    @SerializedName("weight") val weight: Int?
)

/**
 * Representa as estatísticas de um Pokémon.
 *
 * @param base_stat O valor base da estatística.
 * @param effort O esforço para aumentar a estatística.
 * @param stat A estatística (ex: Ataque, Defesa).
 */
data class PokeStats(
    @SerializedName("base_stat") val base_stat: Int?,
    @SerializedName("effort") val effort: Int?,
    @SerializedName("stat") val stat: PokeNameAndUrl
)

/**
 * Representa os tipos de um Pokémon.
 *
 * @param slot A posição do tipo no Pokémon (primeiro, segundo, etc.).
 * @param type O tipo do Pokémon (ex: Fogo, Água).
 */
data class PokeTypes(
    @SerializedName("slot") val slot: Int?,
    @SerializedName("types") val type: PokeNameAndUrl
)

/**
 * Representa o nome e a URL de um recurso.
 *
 * @param name O nome do recurso (ex: nome do Pokémon, nome do tipo).
 * @param url A URL do recurso, caso haja.
 */
data class PokeNameAndUrl(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)

/**
 * Representa as habilidades de um Pokémon.
 *
 * @param ability A habilidade do Pokémon.
 * @param is_hidden Indica se a habilidade é secreta (oculta).
 * @param slot A posição da habilidade.
 */
data class PokeAbilities(
    @SerializedName("ability") val ability: PokeNameAndUrl,
    @SerializedName("is_hidden") val is_hidden: Boolean?,
    @SerializedName("slot") val slot: Int?
)

/**
 * Representa os gritos de um Pokémon.
 *
 * @param latest O grito mais recente do Pokémon.
 * @param legacy O grito antigo ou legado do Pokémon.
 */
data class PokeCries(
    @SerializedName("latest") val latest: String?,
    @SerializedName("legacy") val legacy: String?
)

/**
 * Representa os índices dos jogos onde o Pokémon aparece.
 *
 * @param game_index Índice do Pokémon no jogo.
 * @param version A versão do jogo.
 */
data class PokeGameIndices(
    @SerializedName("game_index") val game_index: Int?,
    @SerializedName("version") val version: PokeNameAndUrl
)

/**
 * Representa os itens que o Pokémon pode segurar.
 *
 * @param item O item que o Pokémon pode segurar.
 * @param version_details Detalhes sobre o item e a versão do jogo.
 */
data class PokeHeldItem(
    @SerializedName("item") val item: PokeNameAndUrl,
    @SerializedName("version_details") val version_details: List<PokeVersionDetails>
)

/**
 * Representa os detalhes de uma versão do jogo em que o item pode ser encontrado.
 *
 * @param rarity A raridade do item.
 * @param version A versão do jogo onde o item aparece.
 */
data class PokeVersionDetails(
    @SerializedName("rarity") val rarity: Int?,
    @SerializedName("version") val version: PokeNameAndUrl
)

/**
 * Representa os movimentos que o Pokémon pode aprender.
 *
 * @param move O movimento que o Pokémon pode aprender.
 * @param version_group_details Detalhes sobre os grupos de versões e como o Pokémon aprende o movimento.
 */
data class PokeMoves(
    @SerializedName("move") val move: PokeNameAndUrl?,
    @SerializedName("version_group_details") val version_group_details: List<PokeVersionGroupDetils>
)

/**
 * Representa os detalhes sobre o grupo de versões em que o Pokémon aprende o movimento.
 *
 * @param level_learned_at O nível em que o Pokémon aprende o movimento.
 * @param move_learn_method O método pelo qual o Pokémon aprende o movimento.
 * @param order A ordem em que o movimento é aprendido.
 * @param version_group O grupo de versão do jogo.
 */
data class PokeVersionGroupDetils(
    @SerializedName("level_learned_at") val level_learned_at: Int?,
    @SerializedName("move_learn_method") val move_learn_method: PokeNameAndUrl,
    @SerializedName("order") val order: Int? = null,
    @SerializedName("version_group") val versionGroup: PokeNameAndUrl
)
