package com.pokemon_app.presentation.viewmodel

import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDbRepository: PokemonDbRepository
): GenericPokemonViewModel(pokemonService, pokemonDbRepository) {
}