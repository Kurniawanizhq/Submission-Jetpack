package com.eone.submission1

import androidx.lifecycle.LiveData
import com.eone.submission1.model.DataEntity

interface ContentDataSource {
    fun getMovie() : LiveData<List<ItemListResponse>>
    fun getDetailMovie(movieId : Int): LiveData<ItemDetailResponse>
    fun getTvShow() : LiveData<List<ItemListResponse>>
    fun getTvShowDetail(movieId: Int) : LiveData<ItemDetailResponse>
}