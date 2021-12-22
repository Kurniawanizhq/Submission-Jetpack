package com.eone.submission1.ui.home

import com.eone.submission1.ItemListResponse

interface HomeCallback {
    fun onItemClicked(data: ItemListResponse)
}