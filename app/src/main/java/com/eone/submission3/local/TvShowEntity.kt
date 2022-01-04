package com.eone.submission3.local

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
    @SerializedName("id")
    val id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvshow_id")
    val movieId : Int = 0 ,

    @ColumnInfo(name = "tvshow_poster")
    val posterPath: String? = null,

    @ColumnInfo(name = "tvshow_bg")
    val backdropPath: String? = null,

    @ColumnInfo(name = "tv_name")
    val name: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate : String? = null,

    @ColumnInfo(name = "genre")
    var genre : String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage : Double = 0.0,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite : Boolean = false
) : Parcelable
