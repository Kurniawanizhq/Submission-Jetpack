package com.eone.submission1

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genres")
    val genres : List<Genre>
)
