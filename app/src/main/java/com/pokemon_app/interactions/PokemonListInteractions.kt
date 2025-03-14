package com.pokemon_app.interactions

import com.pokemon_app.domain.model.Pokemon


sealed class GenericAction {

    sealed class PokemonAction {
        data class LoadPokemons(val limit: Int = 151) : GenericAction()
    }

    sealed class ListPokemonAction {
        data class SearchPokemons(
            val query: String,
            var pokemons: MutableList<Pokemon>
        ) : GenericAction()
    }

}


sealed class GenericStates {

    data class ShowLoading(var isLoading: Boolean) : GenericStates()

    data class ListPokemons(val pokemons: List<Pokemon> = emptyList(), val error: String? = null) : GenericStates()

    data class SearchPokemons(var filteredPokemons: List<Pokemon> = emptyList()): GenericStates()

}