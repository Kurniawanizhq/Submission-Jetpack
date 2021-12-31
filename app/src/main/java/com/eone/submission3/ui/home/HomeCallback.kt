package com.eone.submission3.ui.home

import com.eone.submission3.data.response.ItemListResponse

interface HomeCallback {
    fun onItemClicked(data: ItemListResponse)
}