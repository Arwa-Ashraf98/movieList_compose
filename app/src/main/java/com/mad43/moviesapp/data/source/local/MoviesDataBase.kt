package com.mad43.moviesapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mad43.moviesapp.data.models.entity.MovieEntity
import com.mad43.moviesapp.data.models.entity.MoviesRemoteKeys

@Database(entities = [MovieEntity::class  , MoviesRemoteKeys::class] , exportSchema = false , version =4)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao
    abstract fun getMoviesRemoteKeysDao() : MoviesRemoteKeysDao
}
