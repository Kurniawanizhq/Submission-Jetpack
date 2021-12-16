package com.eone.submission1.ui.home

import com.eone.submission1.model.DataEntity

interface HomeCallback {
    fun onItemClicked(data: DataEntity)
}