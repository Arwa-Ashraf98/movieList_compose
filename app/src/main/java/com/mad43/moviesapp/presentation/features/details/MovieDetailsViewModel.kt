package com.mad43.moviesapp.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mad43.moviesapp.R
import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.interactors.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase, ) : ViewModel() {


    private var _detailsState = MutableStateFlow(MovieDetailsState())
    val detailsState = _detailsState.asStateFlow()


    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _detailsState.update { it.copy(isLoading = true) }

            getMovieDetailsUseCase(movieId = movieId).collectLatest { res ->
                when (res) {
                    is ResourceResult.SUCCESS -> {
                        _detailsState.update {
                            it.copy(isLoading = false, movie = res.data, error = null)
                        }
                    }

                    is ResourceResult.ERROR -> {
                        _detailsState.update {
                            it.copy(
                                isLoading = false,
                                movie = null,
                                error = res.error?.localizedMessage ?: "Something Went Wrong"
                            )
                        }
                    }
                }
            }
        }
    }
}