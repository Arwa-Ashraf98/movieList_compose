package com.mad43.moviesapp.presentation.features.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.mad43.moviesapp.domain.interactors.GetMoviesUseCase
import com.mad43.moviesapp.presentation.models.toDisplayedMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase,
    @Named("defaultDispatcher") private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val moviesPagingFlow = getMoviesUseCase()
        .cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.map { movie -> movie.toDisplayedMovie() }
        }.flowOn(defaultDispatcher)
}