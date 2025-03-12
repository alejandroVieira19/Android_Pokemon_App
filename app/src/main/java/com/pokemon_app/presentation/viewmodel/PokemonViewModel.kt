package com.pokemon_app.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.utils.PokemonService
import kotlinx.coroutines.launch

class PokemonViewModel(private val pokemonService: PokemonService) : ViewModel() {

    // LiveData para a lista de Pokémons
    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    // LiveData para erros
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Função pública para buscar todos os Pokémons
    fun fetchAllPokemons(limit: Int = 151) {
        viewModelScope.launch {
            try {
                val result = pokemonService.getAllPokemons(limit)
                _pokemons.postValue(result)
            } catch (e: Exception) {
                Log.e("ViewModel", e.message.toString())
                _error.postValue(e.message ?: "Erro desconhecido")
            }
        }
    }

    // Função para buscar Pokémon específico
    fun getPokemonByName(name: String): Pokemon? {
        return _pokemons.value?.find { it.pokemonName.equals(name, ignoreCase = true) }
    }
}