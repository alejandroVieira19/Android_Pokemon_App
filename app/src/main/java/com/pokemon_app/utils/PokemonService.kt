package com.pokemon_app.utils

import android.util.Log
import com.pokemon_app.data.model.PokemonResponse
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.domain.repository.IPokemonRepository

class PokemonService(private val pokemonRepository: IPokemonRepository) {

    private val allPokemonsList = mutableListOf<Pokemon>()

    private suspend fun getAllPokemonByGeneration(limit: Int = 151): PokemonResponse? {
        try {
            return pokemonRepository.retrievePokemonList(limit)
        } catch (e: Exception) {
            Log.e("Error in getAllPokemonByGeneration", e.message.toString())
        }
        return null
    }


    private suspend fun createPokemonObjects(limit: Int = 151) {
        try {
            val allPokemons = getAllPokemonByGeneration(limit)

            allPokemons?.results?.forEach { pokemonSummary ->

                val pokemonDetailResponse = pokemonRepository.retrievePokemonDetails(pokemonSummary.url!!)

                pokemonDetailResponse?.let { val pokemon = PokeMapper.mapToDomain(it)
                    Log.d("Pokemon", pokemon.pokemonType.toString())
                    allPokemonsList.add(pokemon)
                }
            }

        } catch (e: Exception) {
            Log.e("Error in createPokemonObjects", e.message.toString())
        }
    }

   suspend fun getAllPokemons(limit: Int = 151): List<Pokemon> {
        createPokemonObjects(limit)
       return allPokemonsList;
    }

    fun getSelectedPokemon(pokemonName: String): Pokemon? {
        return allPokemonsList.find { it.pokemonName.equals(pokemonName, ignoreCase = true) }
    }
}
