package com.pokemon_app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.pokemon_app.domain.service.ConnectivityObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(private val context : Context) : ConnectivityObserver {

    private val _connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.NetworkStatus> {

        return callbackFlow {

            trySend(getInitialNetworkStatus())

            val callback = object : ConnectivityManager.NetworkCallback() {


                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch{trySend(ConnectivityObserver.NetworkStatus.Available)}
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch{trySend(ConnectivityObserver.NetworkStatus.Unavailable)}
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch{trySend(ConnectivityObserver.NetworkStatus.Lost)}
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch{trySend(ConnectivityObserver.NetworkStatus.Losing)}
                }
            }

            _connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                _connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
    // Função auxiliar para obter o status inicial da rede
    private fun getInitialNetworkStatus(): ConnectivityObserver.NetworkStatus {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return if (activeNetwork != null && activeNetwork.isConnected) {
            ConnectivityObserver.NetworkStatus.Available
        } else {
            ConnectivityObserver.NetworkStatus.Unavailable
        }
    }
}

