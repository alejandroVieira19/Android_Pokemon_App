package com.pokemon_app.presentation.viewmodel
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ListPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService, private val context : Context
) : GenericPokemonViewModel(pokemonService, context) {

    override fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.ListPokemonAction.PokemonListByChosenGeneration -> loadPokemonListByGeneration(action.genNumber)
            else -> super.interaction(action)
        }

    }

    private fun loadPokemonListByGeneration(genNumber: Int) {
        genericStateLoading(true)
            viewModelScope.launch {
                try{

                    _pokemonsList.clear()

                    delay(3000)

                    val result = pokemonService.getPokemonByChosenGeneration(genNumber)

                    _state.value = GenericStates.ListPokemons(pokemons = result)

                    _pokemonsList.addAll(result)

                } catch (e:Exception) {
                    _state.value = GenericStates.ListPokemons(error= e.message)
                }finally {
                    genericStateLoading(false)
                }

            }
        }
    }
