package com.pokemon_app.interactions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.ViewTarget
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

    sealed class DetailPokemonAction {
        data class PokemonDetail(val pokemon: Pokemon) : GenericAction()
    }
}


sealed class GenericStates {

    data class ShowLoading(var isLoading: Boolean ? = false) : GenericStates()

    data class ListPokemons(val pokemons: List<Pokemon> = emptyList(), val error: String? = null) : GenericStates()

    data class SearchPokemons(var filteredPokemons: List<Pokemon> = emptyList()): GenericStates()

    data class PokemonDetail(
        val pokemonTypesImage : List<Int> ? = emptyList(),
        val pokemonTypeText: String ? = null,
        val pokemonBackgroundColor: Int ? = 0) : GenericStates()
}