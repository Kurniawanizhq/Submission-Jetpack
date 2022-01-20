package com.eone.submission3.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tab_tvshow")
@Parcelize
data class TvShowEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "tvshow_id")
    val tvshowId : Int = 0 ,

    @ColumnInfo(name = "tvshow_poster")
    val posterPath: String,

    @ColumnInfo(name = "tvshow_bg")
    val backdropPath: String,

    @ColumnInfo(name = "tv_name")
    val name: String,

    @ColumnInfo(name = "duration")
    val duration : String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    var releaseDate : String,

    @ColumnInfo(name = "genre")
    var genre : String,

    @ColumnInfo(name = "vote_average")
    var voteAverage : Double = 0.0,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite : Boolean = false
) : Parcelable
