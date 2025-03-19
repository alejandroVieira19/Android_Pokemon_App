package com.pokemon_app.domain.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.domain.model.Pokemon

interface IPokemonDbRepository {
    suspend fun insertPokemon(pokemon: PokemonEntity): Long
    suspend fun getPokemon(pokeId: Int): PokemonEntity
    suspend fun deletePokemon(pokemon: PokemonEntity): Int
    suspend fun getAllPokemon(): List<PokemonEntity>
    suspend fun checkIfPokemonExists(pokeId: Int): Int
}