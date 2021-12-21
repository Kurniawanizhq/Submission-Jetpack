package com.eone.submission1

import com.google.gson.annotations.SerializedName

data class ItemResponse(
        @SerializedName("result")
    val result: List<ItemListResponse>
)