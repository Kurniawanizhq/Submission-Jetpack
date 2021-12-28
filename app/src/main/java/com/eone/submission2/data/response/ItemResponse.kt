package com.eone.submission2.data.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(
        @SerializedName("results")
    val result: List<ItemListResponse>
)