package com.pokemon_app.domain.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.domain.model.Pokemon

interface IPokemonDbRepository {
    suspend fun insertPokemon(pokemon: PokemonEntity): Int
    suspend fun getPokemon(pokeId: Int): PokemonEntity
    suspend fun deletePokemon(pokemon: PokemonEntity): Int
    suspend fun getAllPokemon(): LiveData<List<PokemonEntity>>
}