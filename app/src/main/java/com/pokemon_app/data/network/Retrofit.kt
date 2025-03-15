package com.pokemon_app.data.network

import com.pokemon_app.utils.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que fornece a instância do Retrofit para comunicação de rede.
 * A instância do Retrofit é inicializada de forma preguiçosa (lazy) para garantir que seja criada
 * apenas quando necessário.
 */
object RetrofitClient {

    fun createRetrofit(baseUrl: String, apiKey: String? = null): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            apiKey?.let {
                addInterceptor(ApiKeyInterceptor(it)) // Adiciona o interceptor se uma chave API for fornecida
            }
        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", apiKey)  // Pode ser ajustado conforme a API
            .build()
        return chain.proceed(request)
    }
}