package com.eone.submission2.data.response

import com.google.gson.annotations.SerializedName

data class ItemDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val genre: List<Genre>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("runtime")
    val duration: Int?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseMovieDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double,

    // for tvShow Detail
    @SerializedName("episode_run_time")
    val epsDuration: List<Int>,

    @SerializedName("first_air_date")
    val releaseTvDate: String?,

    @SerializedName("name")
    val name: String?,
)
