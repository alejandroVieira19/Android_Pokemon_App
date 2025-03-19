package com.pokemon_app.presentation.viewmodel

import android.graphics.Color
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.PokemonService
import com.pokemon_app.utils.getColorForPokemonByType
import com.pokemon_app.utils.getPokemonDetailTypeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val pokemonService: PokemonService,
    private val pokemonDbRepository: PokemonDbRepository) :
    GenericPokemonViewModel(pokemonService, pokemonDbRepository) {

        private lateinit var _pokemon : Pokemon


    override fun interaction(action: GenericAction) {
        when (action) {
            is GenericAction.DetailPokemonAction.PokemonDetail -> showPokemonDetail(action.pokemon)
            else -> super.interaction(action)
        }
    }

    private fun showPokemonDetail(pokemon: Pokemon) {
        _pokemon = pokemon
        _state.value = GenericStates.PokemonDetail(
            pokemon.getPokemonTypesImage(pokemon.pokemonType),
            pokemon.concatenateTypes(pokemon.pokemonType),
            pokemon.getPokemonBackgroundColor(pokemon.pokemonType.get(0)),
            pokemon.getPokemonDoubleForView(pokemon.pokemonWeight, "KG"),
            pokemon.getPokemonDoubleForView(pokemon.pokemonHeight, "M"),
            pokemon.pokemonMovesList
        )
    }

     fun refreshPokemonDetail() {
        showPokemonDetail(_pokemon)
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
            "${pokemonType[0] ?: "Unknown"} / ${pokemonType[1] ?: "Unknown"}"
        } else {
            pokemonType[0] ?: "Unknown"
        }
    }

}