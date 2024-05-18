package com.mad43.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mad43.moviesapp.R
import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.interactors.GetMovieDetailsUseCase
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.presentation.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {

    private var _movieFlow: MutableStateFlow<ScreenUiState<DetailedMovie>> =
        MutableStateFlow(ScreenUiState.Loading)
    val movieFlow: StateFlow<ScreenUiState<DetailedMovie>> = _movieFlow
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _movieFlow.value = ScreenUiState.Loading
            when (val res = getMovieDetailsUseCase(movieId = movieId)) {
                is ResourceResult.SUCCESS -> {
                    _movieFlow.update { ScreenUiState.Success(res.data) }
                }

                is ResourceResult.ERROR -> {
                    _movieFlow.update {
                        ScreenUiState.Error(
                            res.error?.localizedMessage ?: "Something Went Wrong"
                        )
                    }

                }
            }

        }
    }

    fun checkPopularity(popularity: Int): Int {
        return if (popularity <= 500) {
            R.string.not_popular
        } else if (popularity in 501..1000) {
            R.string.known
        } else if (popularity in 1001..2000) {
            R.string.popular
        } else {
            R.string.very_popular
        }
    }
}