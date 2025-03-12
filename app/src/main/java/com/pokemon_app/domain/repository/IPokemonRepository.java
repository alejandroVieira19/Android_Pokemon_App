package com.pokemon_app.domain.repository;

import com.pokemon_app.data.model.PokemonDetailResponse;
import com.pokemon_app.data.model.PokemonResponse;

public interface IPokemonRepository {
    // Método para pegar a lista de Pokémons
    void retrievePokemonList(int limit, RepositoryCallback<PokemonResponse> callback);

    // Método para pegar os detalhes de um Pokémon específico
    void retrievePokemonDetails(int idOrName, RepositoryCallback<PokemonDetailResponse> callback);

    // Interface de callback para retornar os resultados ou erros
    interface RepositoryCallback<T> {
        void onSuccess(T result);
        void onError(String error);
    }
}
