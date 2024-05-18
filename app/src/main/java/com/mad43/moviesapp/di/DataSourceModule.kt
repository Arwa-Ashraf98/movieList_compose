package com.mad43.moviesapp.di

import com.mad43.moviesapp.data.source.remote.IMovieRemoteDataSource
import com.mad43.moviesapp.data.source.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMovieRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): IMovieRemoteDataSource

}