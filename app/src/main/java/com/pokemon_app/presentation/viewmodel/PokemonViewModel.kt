package com.pokemon_app.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService
) : ViewModel() {
    // LiveData para a lista de Pokémons
    private val _pokemonsData = MutableLiveData<PokemonData>()

    val pokemonsData: LiveData<PokemonData> get() =  _pokemonsData

    data class PokemonData(
        var isLoading: Boolean = true,
        val pokemons : List<Pokemon> ? = null,
        val error: String ? = null
    )


    fun fetchAllPokemons(limit: Int = 151) {
        // para evitar duplicação na lista
        if(_pokemonsData.value?.pokemons == null) {

            _pokemonsData.value = PokemonData(isLoading = true)
            viewModelScope.launch {
                try {
                    val result = pokemonService.getAllPokemons(limit)
                    _pokemonsData.value = PokemonData(
                        isLoading = false,
                        pokemons = result,
                        error = null
                    )
                } catch (e: Exception) {
                    Log.e("ViewModel", e.message.toString())
                    _pokemonsData.value = PokemonData(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    // Função para buscar Pokémon específico
    fun getPokemonByName(name: String): Pokemon? {
        return _pokemonsData.value?.pokemons?.find { it.pokemonName.equals(name, ignoreCase = true) }
    }
}