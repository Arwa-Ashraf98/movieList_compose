package com.mad43.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mad43.moviesapp.presentation.Navigation
import com.mad43.moviesapp.ui.theme.MoviesAppTheme
import com.mad43.moviesapp.utlis.NetworkChecker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isOnline = NetworkChecker().isOnline()
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                Scaffold( modifier = Modifier.fillMaxSize()) {
                    Navigation(isNetworkConnected = isOnline)
                }
            }
        }
    }
}



