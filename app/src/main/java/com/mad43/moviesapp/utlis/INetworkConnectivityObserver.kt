package com.mad43.moviesapp.utlis

import kotlinx.coroutines.flow.Flow

interface INetworkConnectivityObserver {

    fun observeNetworkConnection(): Flow<NetworkStatus>
}