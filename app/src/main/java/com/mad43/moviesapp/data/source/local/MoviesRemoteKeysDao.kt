package com.mad43.moviesapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mad43.moviesapp.data.models.entity.MoviesRemoteKeys

@Dao
interface MoviesRemoteKeysDao {
    @Query("SELECT * FROM movies_remote_key_table WHERE movie_id =:id")
    suspend fun getRemoteKeysByMovieId(id: Int): MoviesRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MoviesRemoteKeys>)

    @Query("DELETE FROM movies_remote_key_table")
    suspend fun deleteAllRemoteKeys()


    @Query("Select created_at From movies_remote_key_table Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}