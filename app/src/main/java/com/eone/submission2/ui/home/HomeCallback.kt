package com.eone.submission2.ui.home

import com.eone.submission2.data.response.ItemListResponse

interface HomeCallback {
    fun onItemClicked(data: ItemListResponse)
}