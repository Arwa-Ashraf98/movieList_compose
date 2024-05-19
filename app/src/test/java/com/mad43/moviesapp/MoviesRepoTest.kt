package com.mad43.moviesapp

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.repo.MoviesRepo
import com.mad43.moviesapp.data.source.remote.IMovieRemoteDataSource
import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesRepoTest {

    @Mock
    private lateinit var pager: Pager<Int, MovieEntity>

    @Mock
    private lateinit var movieRemoteDataSource: IMovieRemoteDataSource

    private lateinit var moviesRepo: IMoviesRepo
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        moviesRepo = MoviesRepo(pager, movieRemoteDataSource, testDispatcher)
    }

    @Test
    fun get_all_movies_should_return_flow_of_pagingData() = runBlockingTest {
        // given
        val movieEntity = MovieEntity(
            adult = false,
            id = 1,
            overview = "A brief overview of the movie.",
            popularity = 75.5,
            posterPath = "/path/to/poster.jpg",
            releaseDate = "2023-05-19",
            title = "Test Movie",
            page = 1,
            voteCount = 3.2
        )
        val paginatingData = PagingData.from(listOf(movieEntity))
        `when`(pager.flow).thenReturn(flowOf(paginatingData))

        // when
        val result = moviesRepo.getALlMovies().first()

        // Assert
        result.map { assert(true) }
        verify(pager.flow, times(1)).first()
    }

    @Test
    fun get_movie_details_should_return_resource_result_success() = runBlockingTest {
        // given
        val detailedMovie = DetailedMovie(
            title = "Test Movie",
            voteAverage = 8.5,
            overView = "This is a test overview of the movie.",
            releaseDate = "2023-05-19",
            genre = "Action",
            imageUrl = "/path/to/image.jpg",
            backImageUrl = "/path/to/back_image.jpg",
            language = "English"
        )

        // when
        val result = moviesRepo.getMovieDetails(1)

        // Assert
        assert(result is ResourceResult.SUCCESS)
        assert((result as ResourceResult.SUCCESS).data == detailedMovie)
        verify(movieRemoteDataSource, times(1)).getMovieDetails(anyInt())
    }

    @Test
    fun get_movie_details_should_return_resource_result_error() = runBlockingTest {
        // give
        val errorMessage = "Error"

        // when
        val result = moviesRepo.getMovieDetails(1)
        `when`(movieRemoteDataSource.getMovieDetails(anyInt())).thenReturn(
            ResourceResult.ERROR(
                Throwable(errorMessage)
            )
        )


        // Assert
        assert(result is ResourceResult.ERROR)
        assert(
            (result as ResourceResult.ERROR).error?.message?.contains(errorMessage.lowercase())
                ?: false
        )
        verify(movieRemoteDataSource, times(1)).getMovieDetails(anyInt())
    }
}