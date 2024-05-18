package com.mad43.moviesapp.domain.repo

import androidx.paging.PagingData
import com.mad43.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviesRepo {

    fun getALlMovies() : Flow<PagingData<Movie>>
}