package com.mad43.moviesapp

import com.mad43.moviesapp.data.models.response.MovieResponse
import com.mad43.moviesapp.data.source.remote.IMovieRemoteDataSource
import com.mad43.moviesapp.domain.ResourceResult

class MovieRemoteDataSourceTest(private val movieResponse: MovieResponse? = null) :
    IMovieRemoteDataSource {

    override suspend fun getMovieDetails(movieId: Int): ResourceResult<MovieResponse?> {
        return ResourceResult.SUCCESS(movieResponse)
    }
}