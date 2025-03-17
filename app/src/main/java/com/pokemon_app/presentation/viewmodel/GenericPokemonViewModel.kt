package com.pokemon_app.presentation.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.service.ConnectivityObserver
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.NetworkConnectivityObserver
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class GenericPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService // Injeção de dependência do PokemonService
) : ViewModel() {


    protected val _state = MutableLiveData<GenericStates>()

    private val _pokemonsList = mutableListOf<Pokemon>()

    val state: LiveData<GenericStates> get() = _state

    private lateinit var _connectivityObserver: ConnectivityObserver


    open fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.PokemonAction.LoadPokemons -> loadPokemons(action.limit)
            is GenericAction.PokemonAction.NetworkConnection -> internetConnection(action.context)
            else -> {}
        }
   }

    private fun internetConnection(context: Context) {
        // Inicia a coleta de dados no viewModelScope
        viewModelScope.launch {
            _connectivityObserver = NetworkConnectivityObserver(context)

            // Coleta os status de rede subsequentes
            _connectivityObserver.observe().collect { status ->
                _state.value = GenericStates.NetworkConnection(status)

            }
        }
    }

    private fun loadPokemons(limit: Int) {
        if(_pokemonsList.isEmpty()) {
            _state.value = GenericStates.ShowLoading(true)
            viewModelScope.launch {
                try {
                    val result = pokemonService.getAllPokemons(limit)
                    _state.value = GenericStates.ListPokemons(pokemons = result)
                    _pokemonsList.addAll(result)
                } catch (e: Exception) {
                    _state.value = GenericStates.ListPokemons(error = e.message)
                } finally {
                    _state.value = GenericStates.ShowLoading(false)
                }
            }
        } else {
            _state.value = GenericStates.ListPokemons(pokemons = _pokemonsList)
        }
    }

    fun getSelectedPokemon(pokemonName: String): Pokemon? {
        return _pokemonsList.find { it.pokemonName.equals(pokemonName, ignoreCase = true) }
    }
}