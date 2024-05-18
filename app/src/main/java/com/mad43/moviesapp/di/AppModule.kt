package com.mad43.moviesapp.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.paging.MoviesRemoteMediator
import com.mad43.moviesapp.data.source.local.MoviesDataBase
import com.mad43.moviesapp.data.source.remote.MoviesApi
import com.mad43.moviesapp.utlis.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesRemoteMediator(
        moviesApi: MoviesApi,
        moviesDataBase: MoviesDataBase
    ): MoviesRemoteMediator {
        return MoviesRemoteMediator(moviesApi = moviesApi, moviesDataBase = moviesDataBase)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(
        moviesMediator: MoviesRemoteMediator,
        moviesDataBase: MoviesDataBase
    ): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ITEM_PER_PAGE ,
                prefetchDistance = 10,
                initialLoadSize = Constants.ITEM_PER_PAGE ,
            ),
            remoteMediator = moviesMediator,
            pagingSourceFactory = {
                moviesDataBase.getMovieDao().getMovies()
            }
        )
    }
}