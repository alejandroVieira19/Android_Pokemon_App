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

     override fun interaction(action: GenericAction) {
        when (action) {
            is GenericAction.ListPokemonAction.SearchPokemons -> searchPokemons(action.query, action.pokemons)
            else -> super.interaction((action))
        }
    }


     fun searchPokemons(query: String, pokemons: List<Pokemon>) {
         val filteredPokemons = pokemons.filter { it.pokemonName.lowercase().contains(query.lowercase()) }

         _state.value = GenericStates.SearchPokemons(filteredPokemons)
     }
}