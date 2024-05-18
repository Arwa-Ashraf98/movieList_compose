package com.mad43.moviesapp.data.models.dto

class MoviesResponse (
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)