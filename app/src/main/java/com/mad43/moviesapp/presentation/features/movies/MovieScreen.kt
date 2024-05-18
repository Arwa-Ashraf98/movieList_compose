package com.mad43.moviesapp.presentation.features.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mad43.moviesapp.R
import com.mad43.moviesapp.presentation.Screen
import com.mad43.moviesapp.presentation.common.ProgressBar
import com.mad43.moviesapp.presentation.common.showToast
import com.mad43.moviesapp.presentation.models.DisplayedMovie

@Composable
fun DisplayedMovieScreen(navController: NavController , isNetworkConnected: Boolean) {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val movies = moviesViewModel.moviesPagingFlow.collectAsLazyPagingItems()
    MovieScreen(movies = movies, navController , isNetworkConnected = isNetworkConnected)
}

@Composable
fun MovieScreen(movies: LazyPagingItems<DisplayedMovie>, navController: NavController , isNetworkConnected : Boolean) {
    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState) {
        if (movies.loadState.refresh is LoadState.Error) {
            showToast(context = context, message = "Error: " + (movies.loadState.refresh as LoadState.Error).error.message)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        if (movies.loadState.refresh is LoadState.Loading) {
            ProgressBar(isDisplayed = true)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
                items(movies) { displayedMovie ->
                    MovieItem(
                        movie = displayedMovie ?: DisplayedMovie(),
                        action = {
                            if (isNetworkConnected){
                                navController.navigate(
                                    Screen.MovieDetailsScreen.withArgs(
                                        displayedMovie?.movieId.toString()
                                    )
                                )
                            } else {
                                showToast(context = context , message = context.getString(R.string.error_connect_to_network))
                            }

                        })
                }
            }
        }
    }
}
