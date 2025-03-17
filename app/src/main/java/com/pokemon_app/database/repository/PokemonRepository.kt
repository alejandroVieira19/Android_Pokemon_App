package com.pokemon_app.database.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.database.room.PokemonDbDao
import com.pokemon_app.domain.repository.IPokemonDbRepository

class PokemonRepository(private val pokemonDb: PokemonDB) : IPokemonDbRepository {
    override suspend fun insertPokemon(pokemon: PokemonEntity): Int {
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
}
