package com.pokemon_app.domain.service

import kotlinx.coroutines.flow.Flow

/**
 * Interface para observar o status de conectividade de rede.
 *
 * Esta interface permite monitorar a conectividade de rede de forma reativa, utilizando o conceito de
 * fluxo (Flow). Ela fornece informações sobre a disponibilidade da rede e suas mudanças, como se
 * a rede está disponível, indisponível ou se a conexão está sendo perdida.
 */
interface ConnectivityObserver {

    /**
     * Observa as mudanças no status de conectividade de rede.
     *
     * @return Um [Flow] que emite o status atual da rede sempre que ele muda.
     * O [Flow] irá emitir valores de [NetworkStatus], que indicam o status da rede.
     */
    fun observe(): Flow<NetworkStatus>

    /**
     * Enumeração que representa os diferentes status de conectividade de rede.
     */
    enum class NetworkStatus {
        /**
         * A rede está disponível e funcionando normalmente.
         */
        Available,

        /**
         * A rede está indisponível.
         */
        Unavailable,

        /**
         * A conexão está sendo perdida.
         */
        Losing,

        /**
         * A rede foi perdida, ou seja, a conexão foi completamente interrompida.
         */
        Lost
    }
}
