package com.pokemon_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.interactions.PokeDbEnum
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesPokemonViewModel @Inject constructor(private val pokemonService: PokemonService,
                                                    private val pokemonDbRepository: PokemonDbRepository
): GenericPokemonViewModel(pokemonService, pokemonDbRepository) {

    override fun interaction(action: GenericAction) {
        when (action) {
            is GenericAction.FavoritePokemonAction.GetAllPokemons -> getAllPokemons()
            else -> super.interaction(action)
        }
    }

    fun getAllPokemons() {
        viewModelScope.launch {
            genericStateLoading(true)
            try {
                var pokemonList: List<PokemonEntity>
                withContext(Dispatchers.IO) {
                    pokemonList = pokemonDbRepository.getAllPokemon()
               }
                _state.value = GenericStates.ListPokemons(pokemonList.map { PokeMapper.mapFromEntityToDomain(it)})
            } catch (e: Exception) {
                _state.value = GenericStates.ListPokemons(emptyList(), e.message.toString())
            } finally {
                delay(3000)
                genericStateLoading(false)
            }
        }
    }
}