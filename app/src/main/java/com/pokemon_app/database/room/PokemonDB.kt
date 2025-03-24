package com.pokemon_app.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pokemon_app.database.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false )
abstract class PokemonDB: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDbDao
}