package com.eone.submission1

import com.google.gson.annotations.SerializedName

data class ItemDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("genres")
    val genre : List<Genre>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("vote_average")
    val vote_average: Double
)
