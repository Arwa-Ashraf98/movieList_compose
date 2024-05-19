package com.mad43.moviesapp

import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.interactors.GetMovieDetailsUseCase
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesDetailsUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private  var getMoviesDetailsUseCase: GetMovieDetailsUseCase?= null


    @get:Rule
    @ExperimentalCoroutinesApi
    val main = MainCoroutineRule()

    @Before
    fun setup() {
        getMoviesDetailsUseCase = GetMovieDetailsUseCase(moviesRepo)
    }

    @After
    fun tearDown() {
        getMoviesDetailsUseCase = null
    }

    @Test
    fun invoke_should_return_success_when_repo_returns_success() = runTest {
        // given
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
        `when`(moviesRepo.getMovieDetails(movieId)).thenReturn(successResult)

        // when
        val resultFlow = getMoviesDetailsUseCase?.invoke(movieId)
        val result = resultFlow?.first()

        // Assert
        assert(result is ResourceResult.SUCCESS)
        assert((result as ResourceResult.SUCCESS).data == detailedMovie)
        verify(moviesRepo).getMovieDetails(movieId)
    }

    @Test
    fun `invoke should return error when repo returns error`() = runTest {
        // given
        val movieId = 1
        val errorMessage = "Error occurred"
        val errorResult = ResourceResult.ERROR(Throwable(errorMessage))
        `when`(moviesRepo.getMovieDetails(movieId)).thenReturn(errorResult)

        // when
        val resultFlow = getMoviesDetailsUseCase?.invoke(movieId)
        val result = resultFlow?.first()

        // Assert
        assert(result is ResourceResult.ERROR)
        val res = result as ResourceResult.ERROR
        val message = res.error?.message
        assert(message == errorMessage)
        verify(moviesRepo).getMovieDetails(movieId)
    }
}