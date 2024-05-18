package com.mad43.moviesapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mad43.moviesapp.data.mapper.toMovieEntity
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.models.entity.MoviesRemoteKeys
import com.mad43.moviesapp.data.source.local.MoviesDataBase
import com.mad43.moviesapp.data.source.remote.MoviesApi
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDataBase: MoviesDataBase
) : RemoteMediator<Int, MovieEntity>() {

    private val moviesDao = moviesDataBase.getMovieDao()
    private val moviesRemoteKeysDao = moviesDataBase.getMoviesRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (moviesRemoteKeysDao.getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = moviesApi.getMovies(page = page)
            val movies = apiResponse.body()?.results
            val endOfPaginationReached = movies?.isEmpty()

            moviesDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesRemoteKeysDao.deleteAllRemoteKeys()
                    moviesDao.deleteMovies()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached == true) null else page + 1
                val remoteKeys = movies?.map {
                    MoviesRemoteKeys(
                        movieId = it.id ?: 0,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                moviesRemoteKeysDao.addAllRemoteKeys(remoteKeys ?: emptyList())
                val moviesEntity = movies?.onEachIndexed { _, movie ->
                    movie.page = page
                }?.map {
                    it.toMovieEntity()
                }
                moviesDao.insertMovie(moviesEntity ?: emptyList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): MoviesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                moviesRemoteKeysDao.getRemoteKeysByMovieId(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MovieEntity>
    ): MoviesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                moviesRemoteKeysDao.getRemoteKeysByMovieId(id = movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MovieEntity>
    ): MoviesRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            moviesRemoteKeysDao.getRemoteKeysByMovieId(id = movie.id)
        }
    }
}