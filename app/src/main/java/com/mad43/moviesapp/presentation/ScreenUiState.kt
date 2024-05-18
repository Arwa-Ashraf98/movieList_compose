package com.mad43.moviesapp.presentation

sealed class ScreenUiState<out T> {
    data class Success<T>(val data: T) : ScreenUiState<T>()
    data class Error(val error: String) : ScreenUiState<Nothing>()
    data object Loading : ScreenUiState<Nothing>()
}