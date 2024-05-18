package com.mad43.moviesapp.domain.interactors

import androidx.paging.PagingData
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repo : IMoviesRepo) {

    operator fun invoke() : Flow<PagingData<Movie>> = repo.getALlMovies()
}