package com.mad43.moviesapp.common.utlis

import kotlinx.coroutines.flow.Flow

interface INetworkChecker {

    fun isOnlineFlow() : Flow<Boolean>
}