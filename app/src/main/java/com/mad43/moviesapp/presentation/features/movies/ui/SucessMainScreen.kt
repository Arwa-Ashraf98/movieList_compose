package com.mad43.moviesapp.presentation.features.movies.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.mad43.moviesapp.R
import com.mad43.moviesapp.app.navigation.Screen
import com.mad43.moviesapp.common.components.ProgressBar
import com.mad43.moviesapp.common.components.showToast
import com.mad43.moviesapp.presentation.models.DisplayedMovie


@Composable
fun SuccessMainScreen(
    movies: LazyPagingItems<DisplayedMovie>,
    isNetworkConnected: Boolean,
    navigation : (movieId : String) -> Unit,
    context: Context
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        if (movies.loadState.refresh is LoadState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ProgressBar(isDisplayed = true)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(movies) { displayedMovie ->
                    MovieItem(movie = displayedMovie ?: DisplayedMovie(), action = { movieId ->
                        if (isNetworkConnected) {
                            navigation.invoke(movieId.toString())
                        } else {
                            showToast(
                                context = context,
                                message = context.getString(R.string.error_connect_to_network)
                            )
                        }

                    })
                }
            }
        }
    }
}