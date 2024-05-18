package com.mad43.moviesapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.mad43.moviesapp.data.mapper.toDetailedMovie
import com.mad43.moviesapp.data.mapper.toMovie
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.source.remote.IMovieRemoteDataSource
import com.mad43.moviesapp.domain.ResourceResult
import com.mad43.moviesapp.domain.models.DetailedMovie
import com.mad43.moviesapp.domain.models.Movie
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MoviesRepo @Inject constructor(
    private val pager: Pager<Int, MovieEntity>,
    private val movieRemoteDataSource: IMovieRemoteDataSource,
    @Named("defaultDispatcher") private val defaultDispatcher: CoroutineDispatcher
) : IMoviesRepo {

    override fun getALlMovies(): Flow<PagingData<Movie>> {
        return pager.flow.map { pagingData ->
            pagingData.map { movieEntity ->  movieEntity.toMovie() }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getMovieDetails(movieId: Int): ResourceResult<DetailedMovie> {
        return withContext(defaultDispatcher) {
            when (val res = movieRemoteDataSource.getMovieDetails(movieId = movieId)) {
                is ResourceResult.SUCCESS -> {
                    val domainMovie = res.data?.toDetailedMovie() ?: DetailedMovie()
                    ResourceResult.SUCCESS(domainMovie)
                }

                is ResourceResult.ERROR -> {
                    ResourceResult.ERROR(res.error)
                }
            }
        }
    }

}