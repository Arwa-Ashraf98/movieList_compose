package com.mad43.moviesapp.domain.models

data class DetailedMovie(
    val title: String = "",
    val voteCount: Double = 0.0,
    val overView: String = "",
    val releaseDate: String = "",
    val genre: String = "",
    val imageUrl: String = ""
)