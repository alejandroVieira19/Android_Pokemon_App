package com.pokemon_app.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pokemon_app.database.PokemonEntity

@Dao
interface PokemonDbDao {
    @Insert
   suspend fun insertPokemon(pokemon: PokemonEntity): Long

    @Query("Select * from poke_table WHERE pokemonId = :pokeKey ")
   suspend fun getPokemon(pokeKey: Int): PokemonEntity

    @Delete
    suspend fun deletePokemon(pokemon: PokemonEntity): Int

    @Query("SELECT * FROM POKE_TABLE")
    fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM poke_table WHERE pokemonId = :pokeId")
    fun checkIfPokemonExists(pokeId: Int): Int
}