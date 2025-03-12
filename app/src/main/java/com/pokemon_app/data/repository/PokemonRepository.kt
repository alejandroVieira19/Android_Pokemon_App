package com.pokemon_app.data.repository;

import android.util.Log
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pokemon_app.data.model.PokemonDetailResponse;
import com.pokemon_app.data.model.PokemonResponse;
import com.pokemon_app.data.network.PokeApiService;
import com.pokemon_app.domain.repository.IPokemonRepository;

import kotlin.coroutines.Continuation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.await
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokeApiService: PokeApiService
) : IPokemonRepository {


    override suspend fun retrievePokemonList(limit: Int): PokemonResponse {
        return try {
            val response = pokeApiService.getPokemonList(limit);
            return response
        } catch (e: Exception) {
            throw Exception("Error getting pokemon list by generation ${e.message}")
        }
    }


    override suspend fun retrievePokemonDetails(url: String): PokemonDetailResponse {
        return try {
            val response = pokeApiService.getPokemonByUrl(url);
            return response
        } catch (e: Exception) {
            throw Exception("Error getting pokemon list by generation ${e.message}")
        }
    }
}
