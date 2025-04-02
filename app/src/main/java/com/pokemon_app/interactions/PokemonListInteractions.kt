package com.pokemon_app.interactions

import android.content.Context
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.PokemonAboutMeDTO
import com.pokemon_app.domain.model.PokemonStatsDTO
import com.pokemon_app.domain.service.ConnectivityObserver

import java.io.Serializable

/**
 * Classe selada que representa as diferentes ações genéricas que podem ser realizadas na aplicação.
 *
 * Essas ações são usadas para descrever os eventos que a aplicação deve tratar, como carregar Pokémons,
 * salvar ou excluir Pokémons favoritos, buscar Pokémons, etc.
 */
sealed class GenericAction {

    /**
     * Ações relacionadas aos Pokémons.
     */
    sealed class PokemonAction {

        /**
         * Ação para carregar a lista de Pokémons.
         */
        object LoadPokemons : GenericAction()

        /**
         * Ação para verificar a conexão de rede, com o contexto da aplicação.
         */
        data class NetworkConnection(val context: Context) : GenericAction()

        /**
         * Ação para salvar um Pokémon como favorito.
         */
        data class SaveFavoritePokemon(val pokemon: Pokemon) : GenericAction()

        /**
         * Ação para excluir um Pokémon dos favoritos.
         */
        data class DeleteFavoritePokemon(val pokemon: Pokemon) : GenericAction()

        /**
         * Ação para buscar Pokémons com base em uma consulta (query).
         * Atualiza a lista de Pokémons conforme os resultados da busca.
         */
        data class SearchPokemons(val query: String, var pokemons: MutableList<Pokemon>) : GenericAction()
    }

    /**
     * Ações relacionadas ao detalhe de um Pokémon.
     */
    sealed class DetailPokemonAction {

        /**
         * Ação para mostrar os detalhes de um Pokémon.
         */
        data class PokemonDetail(val pokemon: Pokemon) : GenericAction()
    }

    /**
     * Ações relacionadas à lista de Pokémons.
     */
    sealed class ListPokemonAction {

        /**
         * Ação para recuperar a lista de Pokémons de uma geração escolhida.
         */
        data class PokemonListByChosenGeneration(val genNumber: Int) : GenericAction()
    }

    /**
     * Ações relacionadas aos Pokémons favoritos.
     */
    sealed class FavoritePokemonAction {

        /**
         * Ação para obter todos os Pokémons favoritos.
         */
        object GetAllPokemons : GenericAction()
    }
}

/**
 * Classe selada que representa os diferentes estados da aplicação.
 *
 * Esses estados são usados para refletir as mudanças no estado da aplicação, como quando a rede está
 * conectada, quando a aplicação está carregando ou para mostrar mensagens ao usuário.
 */
sealed class GenericStates {

    /**
     * Estado que representa a conexão de rede e seu status.
     *
     * @param status O status da rede, como disponível, indisponível, perdendo a conexão ou perdida.
     */
    data class NetworkConnection(var status: ConnectivityObserver.NetworkStatus) : GenericStates()

    /**
     * Estado que indica se a aplicação está carregando.
     *
     * @param isLoading Indica se a aplicação está no estado de carregamento.
     */
    data class ShowLoading(var isLoading: Boolean? = false) : GenericStates()

    /**
     * Estado para mostrar o carregamento específico da base de dados.
     *
     * @param isLoading Indica se o carregamento está ativo.
     * @param pokeEnum Enum que indica a ação de base de dados (salvar, excluir ou obter).
     */
    data class ShowLoadingForDB(var isLoading: Boolean? = true, var pokeEnum: PokeDbEnum) : GenericStates()

    /**
     * Estado para mostrar uma mensagem ao usuário.
     *
     * @param message A mensagem a ser exibida.
     */
    data class ShowMessage(var message: String? = null) : GenericStates()

    /**
     * Estado que contém a lista de Pokémons e um possível erro.
     *
     * @param pokemons A lista de Pokémons.
     * @param error Mensagem de erro, caso haja algum problema na obtenção dos Pokémons.
     */
    data class ListPokemons(val pokemons: List<Pokemon> = emptyList(), val error: String? = null) : GenericStates()

    /**
     * Estado que representa o status da base de dados para um Pokémon.
     *
     * @param pokemonIsFavorite Indica se o Pokémon é favorito.
     * @param message Uma mensagem que pode ser exibida para o usuário.
     */
    data class PokemonDatabase(val pokemonIsFavorite: Boolean? = false, var message: String? = null) : GenericStates()

    /**
     * Estado para filtrar e mostrar Pokémons após uma busca.
     *
     * @param filteredPokemons A lista filtrada de Pokémons.
     */
    data class SearchPokemons(var filteredPokemons: List<Pokemon> = emptyList()) : GenericStates()

    /**
     * Estado que contém detalhes sobre um Pokémon específico.
     *
     * @param pokemonTypesImage Lista de imagens relacionadas aos tipos do Pokémon.
     * @param pokemonTypeText Texto representando os tipos do Pokémon.
     * @param pokemonTextColor Cor do texto para o nome do Pokémon.
     * @param pokemonBackgroundColor Cor de fundo do Pokémon.
     * @param pokemonWeight Peso do Pokémon.
     * @param pokemonHeight Altura do Pokémon.
     * @param pokemonMovesList Lista de movimentos do Pokémon.
     * @param pokemonAttack Ataque do Pokémon.
     * @param pokemonDefense Defesa do Pokémon.
     * @param pokemonHP Pontos de saúde (HP) do Pokémon.
     * @param pokemonSpeed Velocidade do Pokémon.
     */
    data class PokemonDetail(
        val pokemonAboutMeDTO: PokemonAboutMeDTO,
        val pokemonTextColor: Int,
        val pokemonBackgroundColor: Int? = 0,
        val pokemonMovesList: List<String>? = null,
        val pokemonStatsDTO: PokemonStatsDTO? = null,

    ) : GenericStates()
}

/**
 * Enumeração para definir as ações realizadas na base de dados em relação aos Pokémons.
 */
enum class PokeDbEnum {
    /**
     * Ação de salvar um Pokémon na base de dados.
     */
    SAVE,

    /**
     * Ação de excluir um Pokémon da base de dados.
     */
    DELETE,

    /**
     * Ação de buscar Pokémons na base de dados.
     */
    GETTING
}

enum class PokemonTabList{
    ABOUT_ME, MOVES,  STATS;

    override fun toString(): String {
        return when(this) {
            ABOUT_ME -> "About me"
            MOVES -> "Moves"
            STATS -> "Stats"
        }
    }
}

