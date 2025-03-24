package com.pokemon_app.domain.model

import com.google.gson.Gson
import com.pokemon_app.interactions.GenericStates
import java.io.Serializable

/**
 * Classe de modelo que representa um Pokémon.
 *
 * Esta classe contém todas as informações relevantes sobre um Pokémon, incluindo seu ID,
 * nome, altura, peso, tipos, imagens, movimentos e estatísticas. A classe também armazena
 * um campo para marcar o Pokémon como favorito ou não.
 *
 * @property pokemonId ID único do Pokémon.
 * @property pokemonName Nome do Pokémon.
 * @property pokemonHeight Altura do Pokémon em metros.
 * @property pokemonWeight Peso do Pokémon em quilogramas.
 * @property pokemonType Lista de tipos do Pokémon (por exemplo, "fogo", "água", etc.).
 * @property pokemonDetailImageUrlBackground URL da imagem de fundo detalhada do Pokémon.
 * @property pokemonImageUrlCard URL da imagem do Pokémon no card.
 * @property pokemonMovesList Lista de movimentos do Pokémon.
 * @property isPokemonFavorite Indica se o Pokémon está marcado como favorito.
 * @property pokemonAttack A estatística de ataque do Pokémon.
 * @property pokemonDefense A estatística de defesa do Pokémon.
 * @property pokemonHP A estatística de HP (pontos de vida) do Pokémon.
 * @property pokemonSpeed A estatística de velocidade do Pokémon.
 */
data class Pokemon(
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonHeight: Double,
    val pokemonWeight: Double,
    val pokemonType: List<String?>,
    val pokemonDetailImageUrlBackground: String,
    val pokemonImageUrlCard: String,
    val pokemonMovesList: List<String>,
    var isPokemonFavorite: Boolean,
    val pokemonAttack: Int,
    val pokemonDefense: Int,
    val pokemonHP: Int,
    val pokemonSpeed: Int
) : Serializable
