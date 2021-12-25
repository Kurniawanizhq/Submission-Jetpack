package com.eone.submission1

import com.google.gson.annotations.SerializedName

data class ItemListResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("genre_ids")
    val genres : List<Int>,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    //Specially title for TvShow
    @SerializedName("name")
    val name: String?
)
