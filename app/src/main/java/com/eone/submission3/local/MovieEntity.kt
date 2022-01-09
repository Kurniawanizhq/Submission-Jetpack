package com.eone.submission3.local

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tab_movie")
@Parcelize
data class MovieEntity(

//    @PrimaryKey(autoGenerate = true)
//    @SerializedName("id")
//    var id: Int? = null,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int = 0,

    @ColumnInfo(name = "movie_poster")
    var posterPath: String? = null,

    @ColumnInfo(name = "movie_bg")
    var backdropPath: String? = null,

    @ColumnInfo(name = "movie_title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "duration")
    var duration: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable