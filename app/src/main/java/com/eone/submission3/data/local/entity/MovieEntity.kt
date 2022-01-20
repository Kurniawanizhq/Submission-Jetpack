package com.eone.submission3.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tab_movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int = 0,

    @ColumnInfo(name = "movie_poster")
    var posterPath: String,

    @ColumnInfo(name = "movie_bg")
    var backdropPath: String,

    @ColumnInfo(name = "movie_title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
): Parcelable