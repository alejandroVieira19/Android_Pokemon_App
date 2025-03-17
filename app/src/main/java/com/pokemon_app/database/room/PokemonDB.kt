package com.pokemon_app.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pokemon_app.database.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false )
abstract class PokemonDB: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDbDao

    // garante que o banco de dados seja acessado de forma centralizada e que apenas uma
    // instância dele seja criada durante a execução do aplicativo.

    companion object {
        //A ideia é garantir que o banco de dados seja inicializado apenas
        // uma vez durante a vida útil do aplicativo.
        @Volatile
        private var INSTANCE: PokemonDB? = null
        fun getInstance(context: Context): PokemonDB {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDB::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}