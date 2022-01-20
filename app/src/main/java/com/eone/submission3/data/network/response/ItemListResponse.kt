package com.eone.submission3.data.network.response

import com.google.gson.annotations.SerializedName

data class ItemListResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String?,

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
