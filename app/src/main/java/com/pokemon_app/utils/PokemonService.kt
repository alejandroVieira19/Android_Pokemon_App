package com.pokemon_app.utils

import android.util.Log
import com.pokemon_app.data.model.PokemonResponse
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokemonService @Inject constructor(private val pokemonRepository: IPokemonRepository) {

    private val allPokemonsList = mutableListOf<Pokemon>()

    private suspend fun getAllPokemonByGeneration(limit: Int, offset: Int): PokemonResponse? {
        try {
            return pokemonRepository.retrievePokemonList(limit, offset )
        } catch (e: Exception) {
            Log.e("Error in getAllPokemonByGeneration", e.message.toString())
        }
        return null
    }

    private suspend fun createPokemonObjects(limit: Int, offset: Int) {
        try {

            allPokemonsList.clear()

            val allPokemons = getAllPokemonByGeneration(limit, offset)

            allPokemons?.results?.forEach { pokemonSummary ->

                val pokemonDetailResponse = pokemonRepository.retrievePokemonDetails(pokemonSummary.url!!)

                pokemonDetailResponse?.let { val pokemon = PokeMapper.mapToDomain(it)
                    Log.d("Pokemon", pokemon.pokemonName.toString())
                    allPokemonsList.add(pokemon)
                }
            }

        } catch (e: Exception) {
            Log.e("Error in createPokemonObjects", e.message.toString())
        }
    }

   suspend fun getAllPokemons(limit: Int, offset: Int): List<Pokemon> {
        createPokemonObjects(limit, offset)
       return allPokemonsList;
    }

    suspend fun getPokemonByChosenGeneration(id:Int, limit:Int, offset: Int): List<Pokemon> {

        createPokemonObjectsFromChosenGeneration(id, limit, offset)

        return allPokemonsList;
    }

    private suspend fun createPokemonObjectsFromChosenGeneration(id: Int, limit: Int, offset: Int) {
        allPokemonsList.clear()

        try {
            val pokemonGenerationResponse = pokemonRepository.retrievePokemonsByGeneration(id)

            pokemonGenerationResponse?.pokemon_Species?.forEach{ specie ->

                val pokemonDetailResponse = pokemonRepository.retrievePokemonByName(specie.name!!)


                pokemonDetailResponse?.let {
                    val pokemon = PokeMapper.mapToDomain(it)
                    allPokemonsList.add(pokemon)
                }
            }

        }catch (e:Exception) {
            Log.e("Error in getPokemonByChosenGeneration", e.message.toString())
        }

    }

}
