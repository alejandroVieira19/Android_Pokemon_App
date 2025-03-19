package com.pokemon_app.interactions
import android.content.Context
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.service.ConnectivityObserver


sealed class GenericAction {

    sealed class PokemonAction {
        data class LoadPokemons(val limit: Int = 151) : GenericAction()
        data class NetworkConnection(val context: Context) : GenericAction()
        data class SaveFavoritePokemon(val pokemon: Pokemon) : GenericAction()
        data class DeleteFavoritePokemon(val pokemon: Pokemon) : GenericAction()
        data class SearchPokemons(val query: String, var pokemons: MutableList<Pokemon>) : GenericAction()
    }

    sealed class DetailPokemonAction {
        data class PokemonDetail(val pokemon: Pokemon) : GenericAction()
    }

    sealed class FavoritePokemonAction {
        object GetAllPokemons: GenericAction()
    }
}


sealed class GenericStates {

    data class NetworkConnection(var status : ConnectivityObserver.NetworkStatus) : GenericStates()

    data class ShowLoading(var isLoading: Boolean ? = false) : GenericStates()

    data class ShowLoadingForDB(var isLoading: Boolean ? = true, var pokeEnum: PokeDbEnum) : GenericStates()

    data class ShowMessage(var message: String ? = null) : GenericStates()

    data class ListPokemons(val pokemons: List<Pokemon> = emptyList(), val error: String? = null) : GenericStates()

    data class PokemonDatabase(val pokemonIsFavorite: Boolean?=false, var message: String? = null): GenericStates()

    data class SearchPokemons(var filteredPokemons: List<Pokemon> = emptyList()): GenericStates()

    data class PokemonDetail(
        val pokemonTypesImage : List<Int> ? = emptyList(),
        val pokemonTypeText: String ? = null,
        val pokemonBackgroundColor: Int ? = 0,
        val pokemonWeight: String ? = null,
        val pokemonHeight: String?= null,
        val pokemonMovesList : List<String>?= null
        ) : GenericStates()
}

enum class PokeDbEnum {
    SAVE, DELETE, GETTING
}