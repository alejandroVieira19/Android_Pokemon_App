package com.pokemon_app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.database.room.PokemonDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDB {
        return Room.databaseBuilder(
            context,
            PokemonDB::class.java,
            "pokemon_database"
        ).build()
    }

    @Provides
    fun providePokemonDao(db: PokemonDB): PokemonDbDao = db.pokemonDao()
}
