package com.mad43.moviesapp

import androidx.paging.PagingData
import com.mad43.moviesapp.domain.interactors.GetMoviesUseCase
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private  var getMoviesUseCase: GetMoviesUseCase ?= null


    @get:Rule
    @ExperimentalCoroutinesApi
    val main = MainCoroutineRule()


    @Before
    fun setup() {
        getMoviesUseCase = GetMoviesUseCase(moviesRepo)
    }

    @After
    fun tearDown() {
        getMoviesUseCase = null
    }

    @Test
    fun invoke_should_return_flow_of_pagingData() = runBlockingTest {
        // given
        val pagingData = PagingData.from(
            listOf(
                Movie(
                    id = 1,
                    title = "Test Movie",
                    date = "20-03-2022",
                    vote = 2.3,
                    posterImage = "/path/to/poster.jpg"
                )
            )
        )
        `when`(moviesRepo.getALlMovies()).thenReturn(flowOf(pagingData))

        // when
        val result = getMoviesUseCase?.invoke()?.first()

        // Assert
        assert(result is PagingData<Movie>)
        verify(moviesRepo, times(1)).getALlMovies()
    }
}