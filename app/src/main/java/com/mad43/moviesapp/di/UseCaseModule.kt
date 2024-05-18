package com.mad43.moviesapp.di

import com.mad43.moviesapp.domain.interactors.GetMovieDetailsUseCase
import com.mad43.moviesapp.domain.interactors.GetMoviesUseCase
import com.mad43.moviesapp.domain.repo.IMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(
        movieRepo: IMoviesRepo
    ): GetMoviesUseCase = GetMoviesUseCase(movieRepo)

    @Provides
    fun provideGetMovieDetailsUseCase(
        movieRepo: IMoviesRepo
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepo)

}