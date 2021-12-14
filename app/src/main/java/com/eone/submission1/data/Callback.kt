package com.eone.submission1.data

import com.eone.submission1.model.DataEntity

interface Callback {
    fun onItemClicked(data: DataEntity)
}