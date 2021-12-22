package com.eone.submission1

import com.google.gson.annotations.SerializedName

data class ItemResponse(
        @SerializedName("results")
    val result: List<ItemListResponse>
)