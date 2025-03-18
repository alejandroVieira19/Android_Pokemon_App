package com.pokemon_app.database.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.domain.repository.IPokemonDbRepository
import javax.inject.Inject

class PokemonDbRepository @Inject constructor(private val pokemonDb: PokemonDB) : IPokemonDbRepository {
    override suspend fun insertPokemon(pokemon: PokemonEntity): Long {
        return when(checkIfPokemonExists(pokemon.pokemonId)) {
           0 -> insert(pokemon)
           else -> throw IllegalStateException("Pokemon ${pokemon.pokemonName} already in your database.")
       }
    }

    suspend fun insert(pokemon: PokemonEntity) : Long {
        pokemon.isPokemonFavorite = true
        return pokemonDb.pokemonDao().insertPokemon(pokemon)
    }

    override suspend fun getPokemon(pokeId: Int): PokemonEntity {
        return pokemonDb.pokemonDao().getPokemon(pokeId)
    }

    override suspend fun deletePokemon(pokemon: PokemonEntity): Int {
        return pokemonDb.pokemonDao().deletePokemon(pokemon)
    }

    override suspend fun getAllPokemon(): LiveData<List<PokemonEntity>> {
        return pokemonDb.pokemonDao().getAllPokemon()
    }

    override suspend fun checkIfPokemonExists(pokeId: Int): Int {

        return pokemonDb.pokemonDao().checkIfPokemonExists(pokeId)
    }
}
