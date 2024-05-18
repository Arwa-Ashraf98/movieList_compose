package com.mad43.moviesapp.presentation.features.movies

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mad43.moviesapp.presentation.Screen
import com.mad43.moviesapp.presentation.models.DisplayedMovie

@Composable
fun DisplayedMovieScreen(navController: NavController) {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val movies = moviesViewModel.moviesPagingFlow.collectAsLazyPagingItems()
    MovieScreen(movies = movies, navController)
}

@Composable
fun MovieScreen(movies: LazyPagingItems<DisplayedMovie>, navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState) {
        if (movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
                items(movies) { displayedMovie ->
                    MovieItem(
                        movie = displayedMovie ?: DisplayedMovie(),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.MovieDetailsScreen.withArgs(
                                        displayedMovie?.movieId.toString()
                                    )
                                )
                            })
                }
            }
        }
    }
}
