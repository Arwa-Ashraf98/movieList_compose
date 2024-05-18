package com.mad43.moviesapp.di

import com.mad43.moviesapp.data.repo.MoviesRepo
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepo(movieRepo: MoviesRepo): IMoviesRepo

}