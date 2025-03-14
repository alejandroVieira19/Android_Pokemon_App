package com.pokemon_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.PokemonService
import kotlinx.coroutines.launch

open class GenericPokemonViewModel(private val pokemonService: PokemonService): ViewModel(){

    protected val _state = MutableLiveData<GenericStates>()

    val state: LiveData<GenericStates> get() = _state


    open fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.PokemonAction.LoadPokemons -> loadPokemons(action.limit)
            else -> {}
        }
   }

    private fun loadPokemons(limit: Int) {
        _state.value = GenericStates.ShowLoading(true)

        viewModelScope.launch {
            try {
                val result = pokemonService.getAllPokemons(limit)
                _state.value = GenericStates.ListPokemons(pokemons = result)
            } catch (e: Exception) {
                _state.value = GenericStates.ListPokemons(error = e.message)
            } finally {
                _state.value = GenericStates.ShowLoading(false)
            }
        }
    }
}