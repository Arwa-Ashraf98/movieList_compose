package com.mad43.moviesapp.presentation

import com.mad43.moviesapp.utlis.Constants
sealed class Screen(val route : String) {
    data object MovieMainScreen : Screen(Constants.MOVIE_LIST_SCREEN)
    data object MovieDetailsScreen : Screen(Constants.MOVIE_DETAILS_SCREEN)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}