package com.mad43.moviesapp.data.mapper

import com.mad43.moviesapp.data.models.response.MovieDto
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.models.response.MovieResponse
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.models.Movie


fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        id = id ?: 0,
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: "",
        title = title ?: "",
        page = page ,
        voteCount = vote_average ?: 0.0
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        date = this.releaseDate,
        posterImage = this.posterPath,
        title = this.title ,
        vote = this.voteCount
    )
}

fun MovieResponse.toDetailedMovie() : DetailedMovie {
    return DetailedMovie(
        title = this.title,
        overView = this.overview ,
        imageUrl = this.poster_path ,
        releaseDate = this.release_date ,
        voteAverage = this.vote_average ,
        genre = this.genres.joinToString(" - ") { it.name }
    )
}