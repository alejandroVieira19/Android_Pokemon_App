package com.pokemon_app.di

import com.pokemon_app.data.network.PokeApiService
import com.pokemon_app.data.repository.PokemonRepository
import com.pokemon_app.database.repository.PokemonDbRepository
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.domain.repository.IPokemonDbRepository
import com.pokemon_app.domain.repository.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(pokeApiService: PokeApiService): IPokemonRepository {
        return PokemonRepository(pokeApiService) // Aqui você fornece a implementação concreta
    }

    @Provides
    @Singleton
    fun providePokemonDbRepository(pokemonDB: PokemonDB): IPokemonDbRepository {
        return PokemonDbRepository(pokemonDB)
    }
}