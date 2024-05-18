package com.mad43.moviesapp.domain.interactors

import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepo: IMoviesRepo
) {

    suspend operator fun invoke(movieId: Int): ResourceResult<DetailedMovie> {
        return movieRepo.getMovieDetails(movieId)
    }
}