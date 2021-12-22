package com.eone.submission1.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class DataEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("runtime")
    val duration: String,
    @SerializedName("genre_ids")
    val genre: String,
    @SerializedName("poster_path")
    val poster: Int,
    @SerializedName("backdrop_path")
    val background : Int
)

