package com.mad43.moviesapp.common.utlis

import kotlinx.coroutines.flow.Flow

interface INetworkConnectivityObserver {

    fun observeNetworkConnection(): Flow<NetworkStatus>
}