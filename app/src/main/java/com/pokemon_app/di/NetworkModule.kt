package com.pokemon_app.di
import com.pokemon_app.data.network.PokeApiService
import com.pokemon_app.data.network.RetrofitClient
import com.pokemon_app.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Fornece a instância do Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.createRetrofit(Config.POKEMON_BASE_URL)
    }

    @Provides
    @Singleton
    fun providePokeApiService(retrofit: Retrofit): PokeApiService {
        return retrofit.create(PokeApiService::class.java)
    }
}
