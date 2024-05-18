package com.mad43.moviesapp.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mad43.moviesapp.common.utlis.Constants


@Entity(tableName = Constants.MOVIE_TABLE)
data class MovieEntity (
    val adult: Boolean,
    @PrimaryKey
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    @ColumnInfo(name = "page")
    var page: Int,
    @ColumnInfo("vote_count")
    var voteCount : Double
)