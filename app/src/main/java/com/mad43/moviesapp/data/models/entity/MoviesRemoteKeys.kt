package com.mad43.moviesapp.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mad43.moviesapp.common.utlis.Constants

@Entity(tableName = Constants.MOVIES_REMOTE_KEY_TABLE)
data class MoviesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    val prevKey: Int?,
    val nextKey: Int? ,
    val currentPage: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

