package com.pokemon_app.utils

import android.util.Log
import com.pokemon_app.data.model.PokemonResponse
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.model.mapper.PokeMapper
import com.pokemon_app.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {

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

    suspend fun getPokemonByChosenGeneration(id:Int): List<Pokemon> {



        createPokemonObjectsFromChosenGeneration(id)

        return allPokemonsList;
    }

    private suspend fun createPokemonObjectsFromChosenGeneration(id: Int) {

        Log.e("YO", allPokemonsList.size.toString())

        allPokemonsList.clear()

        Log.e("YO", allPokemonsList.size.toString())

        try {
            val pokemonGenerationResponse = pokemonRepository.retrievePokemonsByGeneration(id)

            Log.e("YO", "2")

            pokemonGenerationResponse?.pokemon_Species?.forEach{
                specie ->

                Log.e("YO", "3")

                val pokemonDetailResponse = pokemonRepository.retrievePokemonByName(specie.name!!)

                Log.d("POKEMON_RESPONSE", pokemonDetailResponse.toString())

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
