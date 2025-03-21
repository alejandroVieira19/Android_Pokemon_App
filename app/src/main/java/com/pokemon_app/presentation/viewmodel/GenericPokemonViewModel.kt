package com.pokemon_app.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.domain.service.ConnectivityObserver
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.interactions.PokeDbEnum
import com.pokemon_app.utils.NetworkConnectivityObserver
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class GenericPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDbRepository: PokemonDbRepository? = null
) : ViewModel() {


    protected val _state = MutableLiveData<GenericStates>()

    protected val _pokemonsList = mutableListOf<Pokemon>()

    protected var _pokemon_offset : Int = 0

    protected val _batch_size : Int = 20

    val state: LiveData<GenericStates> get() = _state

    private lateinit var _connectivityObserver: ConnectivityObserver


    open fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.PokemonAction.LoadPokemons -> loadPokemons(action.limit)
            is GenericAction.PokemonAction.NetworkConnection -> internetConnection(action.context)
            is GenericAction.PokemonAction.SaveFavoritePokemon -> savePokemonAsFavorite(action.pokemon)
            is GenericAction.PokemonAction.DeleteFavoritePokemon -> deletePokemonAsFavorite(action.pokemon)
            is GenericAction.PokemonAction.SearchPokemons -> searchPokemons(action.query, action.pokemons)
            else -> {}
        }
   }

    // TODO -----> COMO BUSCAR O STRINGS.XML NA VIEWMODEL???
    private fun deletePokemonAsFavorite(pokemon: Pokemon) {
        when(checkIfRepositoryIsNull()) {
            true -> genericStateMessage("Database is null at the moment. Please try again and check the code")
            else -> deletePokemon(pokemon)
        }
    }

    private fun deletePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            genericStateLoadingForDb(true, PokeDbEnum.DELETE)
            try {
                withContext(Dispatchers.IO) { pokemonDbRepository!!.deletePokemon(PokeMapper.mapFromDomainToEntity(pokemon)) }
                delay(3000)
                genericStateLoadingForDb(false, PokeDbEnum.DELETE)
                pokemon.isPokemonFavorite = false
                genericPokemonDatabase(pokemon.isPokemonFavorite, "Pokemon ${pokemon.pokemonName} deleted from database.")

            } catch (e: Exception) {
                delay(3000)
                genericStateLoadingForDb(false, PokeDbEnum.DELETE)
                genericPokemonDatabase(pokemon.isPokemonFavorite, "Error: ${e.message}")
            }
        }
    }

    private fun savePokemonAsFavorite(pokemon:Pokemon) {
        when(checkIfRepositoryIsNull()) {
            true -> genericPokemonDatabase(false,"Database is null at the moment. Please try again and check the code")
            else -> savePokemon(pokemon)
        }
    }

    private fun savePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            genericStateLoadingForDb(true, PokeDbEnum.SAVE)
            try {
                withContext(Dispatchers.IO) { pokemonDbRepository!!.insertPokemon(PokeMapper.mapFromDomainToEntity(pokemon)) }
                delay(3000)
                genericStateLoadingForDb(false, PokeDbEnum.SAVE)
                pokemon.isPokemonFavorite = true
                genericPokemonDatabase(pokemon.isPokemonFavorite, "Pokemon ${pokemon.pokemonName} saved in database.")

            }catch (e: Exception) {
                delay(3000)
                genericStateLoadingForDb(false, PokeDbEnum.SAVE)
                genericPokemonDatabase(pokemon.isPokemonFavorite, "Error: ${e.message}")
            }
        }
    }

    protected fun genericPokemonDatabase(isPokemonFavorite:Boolean,message: String) {
        _state.value = GenericStates.PokemonDatabase(isPokemonFavorite, message)
    }

    protected fun genericStateMessage(message:String)  {
        _state.value = GenericStates.ShowMessage(message)
    }

    protected fun genericStateLoadingForDb(isLoading: Boolean, enum: PokeDbEnum) {
        _state.value = GenericStates.ShowLoadingForDB(isLoading, enum)
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

    protected fun searchPokemons(query: String, pokemons: List<Pokemon>) {
        val filteredPokemons = pokemons.filter { it.pokemonName.lowercase().contains(query.lowercase()) }

        _state.value = GenericStates.SearchPokemons(filteredPokemons)
    }

    private fun loadPokemons(limit: Int) {
        val remainingPokemons = limit - _pokemon_offset

        // Se ainda houver Pokémon para buscar
        if (_pokemonsList.isEmpty() || _pokemon_offset < limit) {
            _state.value = GenericStates.ShowLoading(true)

            viewModelScope.launch {
                try {
                    delay(2000)

                    val batchSize = if (remainingPokemons < _batch_size) remainingPokemons else _batch_size

                    val result = pokemonService.getAllPokemons(batchSize, _pokemon_offset)

                    _state.value = GenericStates.ListPokemons(pokemons = result)

                    _pokemon_offset += batchSize

                    _pokemonsList.addAll(result)

                    Log.d("PokemonList", _pokemonsList.size.toString())

                    _pokemonsList.forEach { pokemon ->
                        Log.d("Pokemon Name", pokemon.pokemonName)  // Aqui `pokemon.name` assume que `name` é a propriedade do Pokémon que contém o nome
                    }

                    delay(1000)

                } catch (e: Exception) {
                    _state.value = GenericStates.ListPokemons(error = e.message)
                } finally {
                    _state.value = GenericStates.ShowLoading(false)
                }
            }
        } else {
            Log.d("ELSE", _pokemonsList.size.toString())
            _state.value = GenericStates.ListPokemons(pokemons = _pokemonsList)
        }
    }
}