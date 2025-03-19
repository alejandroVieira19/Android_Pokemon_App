package com.pokemon_app.presentation.viewmodel
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ListPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService
) : GenericPokemonViewModel(pokemonService) {

}