package com.pokemon_app.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pokemon_app.utils.PokemonService

class PokemonViewModelFactory(private val pokemonService: PokemonService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(pokemonService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
