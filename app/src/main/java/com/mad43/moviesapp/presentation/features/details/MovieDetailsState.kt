package com.mad43.moviesapp.presentation.features.details

import com.mad43.moviesapp.domain.models.DetailedMovie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: DetailedMovie? = null ,
    val error : String ?= ""
)
