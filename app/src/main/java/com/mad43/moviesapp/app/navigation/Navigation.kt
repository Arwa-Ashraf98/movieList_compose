package com.mad43.moviesapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mad43.moviesapp.presentation.features.details.ui.MovieDetailsScreen
import com.mad43.moviesapp.presentation.features.movies.ui.MovieScreen

private const val ID = "id"

@Composable
fun Navigation(isNetworkConnected : Boolean) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MovieMainScreen.route) {
        composable(route = Screen.MovieMainScreen.route) {
            MovieScreen(navController = navController , isNetworkConnected = isNetworkConnected)
        }

        composable(
            route = Screen.MovieDetailsScreen.route + "/{$ID}",
            arguments = listOf(
                navArgument(ID) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })
        ) {
            MovieDetailsScreen(navController = navController)
        }
    }
}