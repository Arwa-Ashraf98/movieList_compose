package com.mad43.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.mad43.moviesapp.data.source.local.MoviesDao
import com.mad43.moviesapp.data.source.local.MoviesDataBase
import com.mad43.moviesapp.data.source.local.MoviesRemoteKeysDao
import com.mad43.moviesapp.common.utlis.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): MoviesDataBase =
        Room.
        databaseBuilder(context, MoviesDataBase::class.java, Constants.MOVIE_DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMoviesDao(movieDataBase: MoviesDataBase): MoviesDao = movieDataBase.getMovieDao()

    @Provides
    @Singleton
    fun provideMoviesRemoteKeysDao(movieDataBase: MoviesDataBase) : MoviesRemoteKeysDao = movieDataBase.getMoviesRemoteKeysDao()
}