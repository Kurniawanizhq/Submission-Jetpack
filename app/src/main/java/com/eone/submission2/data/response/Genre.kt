package com.eone.submission2.data.response

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
