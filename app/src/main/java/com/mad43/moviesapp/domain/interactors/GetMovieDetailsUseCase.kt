package com.mad43.moviesapp.domain.interactors

import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepo: IMoviesRepo
) {

    suspend operator fun invoke(movieId: Int): Flow<ResourceResult<DetailedMovie>> {
        return flowOf(movieRepo.getMovieDetails(movieId))
    }
}