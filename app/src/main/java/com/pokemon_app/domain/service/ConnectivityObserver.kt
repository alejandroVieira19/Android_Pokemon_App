package com.pokemon_app.domain.service

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<NetworkStatus>

    enum class NetworkStatus {
        Available, Unavailable, Losing, Lost
    }
}

