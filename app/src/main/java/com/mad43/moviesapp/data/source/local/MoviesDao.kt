package com.mad43.moviesapp.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mad43.moviesapp.data.models.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table order by page")
    fun getMovies(): PagingSource<Int , MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Query("DELETE FROM movies_table")
    suspend fun deleteMovies()
}