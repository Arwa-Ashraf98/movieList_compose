package com.mad43.moviesapp

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.mad43.moviesapp.app.HiltApplication
import com.mad43.moviesapp.domain.interactors.GetMoviesUseCase
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import com.mad43.moviesapp.presentation.features.movies.viewmodel.MoviesViewModel
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Named

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
@ExperimentalCoroutinesApi
@SmallTest
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private  var getMoviesUseCase: GetMoviesUseCase ?= null


    @get:Rule
    @ExperimentalCoroutinesApi
    val main = MainCoroutineRule()

    private val testDispatcher = StandardTestDispatcher()


    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = GetMoviesUseCase(moviesRepo)
        moviesViewModel = MoviesViewModel(getMoviesUseCase ?: GetMoviesUseCase(moviesRepo), testDispatcher)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun moviesPagingFlow_emits_correct_data() = runTest {
        // Arrange
        val movie =  Movie(
            id = 1,
            title = "Test Movie",
            date = "20-03-2022",
            vote = 2.3,
            posterImage = "/path/to/poster.jpg"
        )
        val pagingData = PagingData.from(listOf(movie))
        `when`(getMoviesUseCase?.invoke()).thenReturn(flowOf(pagingData))


        // Act
        val result = moviesViewModel.moviesPagingFlow.first()
        result.map {
            assert(it.title == "Test Movie")
        }
        // Assert
        verify(getMoviesUseCase, times(1))?.invoke()
    }
}