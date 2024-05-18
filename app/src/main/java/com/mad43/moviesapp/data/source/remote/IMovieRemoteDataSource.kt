package com.mad43.moviesapp.data.source.remote

import com.mad43.moviesapp.data.models.response.MovieResponse
import com.mad43.moviesapp.domain.ResourceResult

interface IMovieRemoteDataSource {
    suspend fun getMovieDetails(movieId : Int) : ResourceResult<MovieResponse?>

}