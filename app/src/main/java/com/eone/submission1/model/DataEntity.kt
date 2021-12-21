package com.eone.submission1.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DataEntity(
    @SerializedName("id")
    val id: String,
    val title: String,
    val overview: String,
    val duration: String,
    val genre: String,
    val poster: Int,
    val background : Int
) : Parcelable

