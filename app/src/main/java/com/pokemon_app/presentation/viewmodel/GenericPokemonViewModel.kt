package com.pokemon_app.presentation.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.domain.service.ConnectivityObserver
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.NetworkConnectivityObserver
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
open class GenericPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDbRepository: PokemonDbRepository? = null
) : ViewModel() {


    protected val _state = MutableLiveData<GenericStates>()

    private val _pokemonsList = mutableListOf<Pokemon>()

    val state: LiveData<GenericStates> get() = _state

    private lateinit var _connectivityObserver: ConnectivityObserver


    open fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.PokemonAction.LoadPokemons -> loadPokemons(action.limit)
            is GenericAction.PokemonAction.NetworkConnection -> internetConnection(action.context)
            is GenericAction.PokemonAction.SaveFavoritePokemon -> savePokemonAsFavorite(action.pokemon)
            is GenericAction.PokemonAction.DeleteFavoritePokemon -> deletePokemonAsFavorite(action.pokemon)
            else -> {}
        }
   }

    private fun deletePokemonAsFavorite(pokemon: Pokemon) {
        when(checkIfRepositoryIsNull()) {
            true -> genericStateMessage("Database is null at the moment. Please try again and check the code")
            else -> deletePokemon(pokemon)
        }
    }

    private fun deletePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
           genericStateLoading(true)
            try {
                pokemon.isPokemonFavorite = false
                withContext(Dispatchers.IO) {
                    pokemonDbRepository!!.deletePokemon(PokeMapper.mapFromDomainToEntity(pokemon))
                }
                genericStateMessage("Pokemon ${pokemon.pokemonName} deleted in database.")
                genericStatePokemonDeleted(true)
            } catch (e: Exception) {
                genericStateMessage("Error saving Pokemon in database.\n" + " Error: ${e.message}")
            } finally {
                genericStateLoading(false)
            }
        }
    }

    private fun genericStatePokemonDeleted(isErased: Boolean) {
        _state.value = GenericStates.DeletedPokemon(isErased)
    }

    private fun savePokemonAsFavorite(pokemon:Pokemon) {
        when(checkIfRepositoryIsNull()) {
            true -> genericStateMessage("Database is null at the moment. Please try again and check the code")
            else -> savePokemon(pokemon)
        }
    }

    private fun savePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            genericStateLoading(true)
            try {
                withContext(Dispatchers.IO) {
                    pokemon.isPokemonFavorite = true
                    pokemonDbRepository!!.insertPokemon(PokeMapper.mapFromDomainToEntity(pokemon))
                }
                genericStateMessage("Pokemon ${pokemon.pokemonName} saved in database.")
                _state.value = GenericStates.PokemonFavorite(pokemon)

            }catch (e: Exception) {
                genericStateMessage("Error saving Pokemon in database.\n" + " Error: ${e.message}")
            } finally {
                genericStateLoading(false)
            }
        }
    }

    protected fun genericStateMessage(message:String)  {
        _state.value = GenericStates.ShowMessage(message)
    }

    protected fun genericStateLoading(isLoading: Boolean) {
        _state.value = GenericStates.ShowLoading(isLoading)
    }

    private fun checkIfRepositoryIsNull() : Boolean {
        return pokemonDbRepository == null
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