package com.pokemon_app.data.repository;

import com.pokemon_app.data.model.PokemonDetailResponse;
import com.pokemon_app.data.model.PokemonResponse;
import com.pokemon_app.data.network.PokeApiService;
import com.pokemon_app.domain.repository.IPokemonRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository implements IPokemonRepository {

    private PokeApiService pokeApiService;

    // Construtor para inicializar o PokeApiService
    public PokemonRepository(PokeApiService apiService) {
        this.pokeApiService = apiService;
    }

    @Override
    public void retrievePokemonList(int limit, RepositoryCallback<PokemonResponse> callback) {
        // Fazendo a chamada assíncrona para obter a lista de Pokémons
        Call<PokemonResponse> call = (Call<PokemonResponse>) pokeApiService.getPokemonList(limit);

        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Chamando o método de sucesso do callback se a resposta for bem-sucedida
                    callback.onSuccess(response.body());
                } else {
                    // Chamando o método de erro do callback em caso de falha
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                // Chamando o método de erro do callback se a requisição falhar
                callback.onError("Failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void retrievePokemonDetails(int idOrName, RepositoryCallback<PokemonDetailResponse> callback) {
        // Fazendo a chamada assíncrona para obter os detalhes de um Pokémon
        Call<PokemonDetailResponse> call = pokeApiService.getPokemonDetails(idOrName);

        call.enqueue(new Callback<PokemonDetailResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailResponse> call, Response<PokemonDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Chamando o método de sucesso do callback se a resposta for bem-sucedida
                    callback.onSuccess(response.body());
                } else {
                    // Chamando o método de erro do callback em caso de falha
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailResponse> call, Throwable t) {
                // Chamando o método de erro do callback se a requisição falhar
                callback.onError("Failure: " + t.getMessage());
            }
        });
    }
}
