package com.eone.submission3.data.network.response

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
