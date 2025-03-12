package com.pokemon_app.data.network

import com.pokemon_app.utils.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que fornece a instância do Retrofit para comunicação de rede.
 * A instância do Retrofit é inicializada de forma preguiçosa (lazy) para garantir que seja criada
 * apenas quando necessário.
 */
object RetrofitInstance {

    // Ela será criada apenas quando acessada pela primeira vez.
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Config.POKEMON_BASE_URL) // URL base para a API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

