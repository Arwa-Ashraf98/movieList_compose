package com.mad43.moviesapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.mad43.moviesapp.data.mapper.toMovie
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class MoviesRepo @Inject constructor(
    private val pager: Pager<Int, MovieEntity> ,
    @Named("defaultDispatcher") private val defaultDispatcher: CoroutineDispatcher
) : IMoviesRepo {

    override fun
            getALlMovies(): Flow<PagingData<Movie>> {
        return pager.flow.map { pagingData -> pagingData.map { movieEntity ->  movieEntity.toMovie() } }.flowOn(defaultDispatcher)
    }

}