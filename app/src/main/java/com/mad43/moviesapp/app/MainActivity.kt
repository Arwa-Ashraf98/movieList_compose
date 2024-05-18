package com.mad43.moviesapp.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mad43.moviesapp.R
import com.mad43.moviesapp.app.navigation.Navigation
import com.mad43.moviesapp.common.components.TextWithBackGround
import com.mad43.moviesapp.common.utlis.NetworkChecker
import com.mad43.moviesapp.common.utlis.NetworkConnectivityObserver
import com.mad43.moviesapp.common.utlis.NetworkStatus
import com.mad43.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isOnline = NetworkChecker().isOnline()
        enableEdgeToEdge()
        setContent {
            val networkStatus by NetworkConnectivityObserver.observeNetworkConnection()
                .collectAsState(
                    initial = NetworkStatus.UNAVAILABLE
                )
            MoviesAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(isNetworkConnected = isOnline)
                    when (networkStatus) {
                        NetworkStatus.UNAVAILABLE -> {
                            TextWithBackGround(
                                show = true, text = stringResource(
                                    id = R.string.network_is_unavailable
                                )
                            )
                        }

                        NetworkStatus.LOST -> {
                            TextWithBackGround(
                                show = true, text = stringResource(
                                    id = R.string.network_is_lost
                                )
                            )
                        }

                        else -> {
                            TextWithBackGround(
                                show = false, text = ""
                            )
                        }
                    }
                }
                Log.e("TAG", "onCreate: ${networkStatus.name}")
            }
        }
    }

}



