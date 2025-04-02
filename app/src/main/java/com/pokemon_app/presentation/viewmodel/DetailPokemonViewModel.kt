package com.pokemon_app.presentation.viewmodel

import android.content.Context
import android.graphics.Color
import com.pokemon_app.R
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.PokemonAboutMeDTO
import com.pokemon_app.domain.model.PokemonStatsDTO
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.interactions.PokemonTabList
import com.pokemon_app.utils.PokemonService
import com.pokemon_app.utils.colours
import com.pokemon_app.utils.getColorForPokemonByType
import com.pokemon_app.utils.getPokemonDetailTypeImage
import com.pokemon_app.utils.getTextColorByPokemonTypeColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val pokemonService: PokemonService,
    private val pokemonDbRepository: PokemonDbRepository,
    private val context: Context
) :
    GenericPokemonViewModel(pokemonService, context, pokemonDbRepository) {

        private lateinit var _pokemon : Pokemon


    override fun interaction(action: GenericAction) {
        when (action) {
            is GenericAction.DetailPokemonAction.PokemonDetail -> showPokemonDetail(action.pokemon)
            else -> super.interaction(action)
        }
    }

    private fun showPokemonDetail(pokemon: Pokemon) {
        _pokemon = pokemon

        _state.value

        _state.value = GenericStates.PokemonDetail(

            PokemonAboutMeDTO(
                pokemon.getPokemonDoubleForView(pokemon.pokemonHeight, "M"),
                pokemon.getPokemonDoubleForView(pokemon.pokemonWeight, "KG"),
                pokemon.concatenateTypes(pokemon.pokemonType),
                pokemon.getPokemonTypesImage(pokemon.pokemonType),
            ),

            pokemon.getTextColorForPokemon(pokemon.pokemonType.get(0)),
             pokemon.getPokemonBackgroundColor(pokemon.pokemonType.get(0)),
            pokemon.pokemonMovesList,

            PokemonStatsDTO(
                pokemon.pokemonAttack,
                pokemon.pokemonDefense,
                pokemon.pokemonHP,
                pokemon.pokemonSpeed
            )

        )
    }

    fun getPokemonTabBarList() : List<PokemonTabList> {
      return PokemonTabList.values().toList()
  }

  private fun Pokemon.getTextColorForPokemon(type:String?): Int {

      val backgroundColor = type?.let { getColorForPokemonByType(it) }

      return getTextColorByPokemonTypeColor(backgroundColor)
  }


    private fun Pokemon.getPokemonDoubleForView(double: Double, string: String): String {
        return "$double $string"
    }

    private fun Pokemon.getPokemonBackgroundColor(type: String?): Int {
        return Color.parseColor(type?.let { getColorForPokemonByType(it) })
    }

    private fun Pokemon.getPokemonTypesImage(pokemonType: List<String?>): List<Int> {
        return getPokemonDetailTypeImage(pokemonType as List<String>)
    }

    // NO MAXIMO UM POKEMON TEM 2 TIPOS
    private fun Pokemon.concatenateTypes(pokemonType: List<String?>): String {
        return if (pokemonType.size == 2) {
            "${pokemonType[0] ?: context.getString(R.string.unknown_pokemon_type)} / ${pokemonType[1] ?:  context.getString(R.string.unknown_pokemon_type)}"
        } else {
            pokemonType[0] ?:  context.getString(R.string.unknown_pokemon_type)
        }
    }

}