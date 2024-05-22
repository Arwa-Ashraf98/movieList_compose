package com.mad43.moviesapp.presentation.features.movies.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mad43.moviesapp.R
import com.mad43.moviesapp.app.navigation.Screen
import com.mad43.moviesapp.common.components.showToast
import com.mad43.moviesapp.presentation.features.movies.viewmodel.MoviesViewModel
import com.mad43.moviesapp.presentation.models.DisplayedMovie

@Composable
fun MovieScreen(navController: NavController, isNetworkConnected: Boolean) {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val movies = moviesViewModel.moviesPagingFlow.collectAsLazyPagingItems()
    MovieContent(movies = movies, navController, isNetworkConnected = isNetworkConnected)
}

@Composable
fun MovieContent(
    movies: LazyPagingItems<DisplayedMovie>,
    navController: NavController,
    isNetworkConnected: Boolean
) {
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState) {
        if (movies.loadState.refresh is LoadState.Error) {
            error = true
            showToast(
                context = context,
                message = context.getString(R.string.error) + (movies.loadState.refresh as LoadState.Error).error.message
            )
        }
    }

    if (error) {
        ErrorMainScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        )
    } else {
        SuccessMainScreen(
            movies = movies,
            isNetworkConnected = isNetworkConnected,
            navigation = {
                navController.navigate(
                    Screen.MovieDetailsScreen.withArgs(it)
                )
            },
            context = context
        )
    }


}
