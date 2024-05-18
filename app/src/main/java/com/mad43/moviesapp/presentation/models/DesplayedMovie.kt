package com.mad43.moviesapp.presentation.models

import com.mad43.moviesapp.domain.models.Movie

data class DisplayedMovie(
    val imageUrl: String ?= null,
    val title: String = "",
    val date: String = "",
    val movieId: Int =0 ,
    val vote : Double = 0.0
)

fun Movie.toDisplayedMovie(): DisplayedMovie {
    return DisplayedMovie(
        imageUrl = this.posterImage,
        title = this.title,
        date = this.date,
        movieId = this.id ,
        vote = this.vote
    )
}