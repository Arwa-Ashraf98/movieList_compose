package com.mad43.moviesapp.domain.models


data class DetailedMovie(
    val title: String = "",
    val voteAverage: Double = 0.0,
    val overView: String = "",
    val releaseDate: String = "",
    val genre: String = "",
    val imageUrl: String = "" ,
    val backImageUrl : String = "" ,
    val language : String = ""
)