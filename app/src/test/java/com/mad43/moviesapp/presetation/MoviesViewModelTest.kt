package com.mad43.moviesapp.presetation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.runner.AndroidJUnit4
import com.mad43.moviesapp.domain.interactors.GetMoviesUseCase
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import com.mad43.moviesapp.presentation.features.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest=Config.NONE)
class MoviesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepo: IMoviesRepo


    private lateinit var getMoviesUseCase: GetMoviesUseCase


    private val testDispatcher = StandardTestDispatcher()


    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getMoviesUseCase = GetMoviesUseCase(moviesRepo)
        moviesViewModel = MoviesViewModel(getMoviesUseCase, testDispatcher)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun moviesPagingFlow_emits_correct_data() = runTest {
        // given
        val movie =  Movie(
            id = 1,
            title = "Test Movie",
            date = "20-03-2022",
            vote = 2.3,
            posterImage = "/path/to/poster.jpg"
        )
        val pagingData = PagingData.from(listOf(movie))
        `when`(getMoviesUseCase.invoke()).thenReturn(flowOf(pagingData))


        // when
        val result = moviesViewModel.moviesPagingFlow.first()
        result.map {
            assert(it.title == "Test Movie")
        }
        // assert
        verify(getMoviesUseCase, times(1))?.invoke()
    }
}