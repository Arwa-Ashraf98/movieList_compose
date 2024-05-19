package com.mad43.moviesapp.presetation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.interactors.GetMovieDetailsUseCase
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.presentation.features.details.viewmodel.MovieDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class MoviesDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Before
    fun setUp()  {
        MockitoAnnotations.openMocks(this)
        movieDetailsViewModel =
            MovieDetailsViewModel(getMovieDetailsUseCase)
    }


    @Test
    fun getMovies_details_emits_movie_details_data() = runBlockingTest {
//        given
        val movieId = 1
        val detailedMovie = DetailedMovie(
            title = "Title",
            voteAverage = 7.5,
            overView = "Overview",
            releaseDate = "2023-05-19",
            genre = "Genre",
            imageUrl = "/image.jpg",
            backImageUrl = "/backImage.jpg",
            language = "en"
        )
        val successResult = ResourceResult.SUCCESS(detailedMovie)
        `when`(getMovieDetailsUseCase.invoke(movieId)).thenReturn(flowOf(successResult))

        // when
        movieDetailsViewModel.getMovie(movieId)

        // Assert
        val updatedState = movieDetailsViewModel.detailsState.drop(1).first()
        assertEquals(updatedState.movie , successResult.data)
    }
}