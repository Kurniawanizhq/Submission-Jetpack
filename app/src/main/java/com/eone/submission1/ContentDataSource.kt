package com.eone.submission1

import androidx.lifecycle.LiveData

interface ContentDataSource {
    fun getMovie() : LiveData<List<ItemDetailResponse>>
    fun getDetailMovie(movieId : String): LiveData<ItemDetailResponse>
    fun getTvShow() : LiveData<List<ItemDetailResponse>>
    fun getTvShowDetail(movieId: String) : LiveData<ItemDetailResponse>
}