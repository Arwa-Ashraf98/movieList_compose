package com.mad43.moviesapp.data.source.remote

import com.mad43.moviesapp.data.models.dto.MovieResponse
import com.mad43.moviesapp.data.models.dto.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    companion object {
        private const val MOVIES_RELATIVE_LINK = "discover/movie"
        private const val MOVIE_DETAILS_RELATIVE_LINK = "movie/{movie_id}"
        private const val ITEM_PER_PAGE = 10
    }

    @GET(MOVIES_RELATIVE_LINK)
    suspend fun getMovies(
        @Query("page") page : Int ,
        @Query("per_page") perPage : Int = ITEM_PER_PAGE
    ) : Response<MoviesResponse>

    @GET(MOVIE_DETAILS_RELATIVE_LINK)
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : Response<MovieResponse>

}