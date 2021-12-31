package com.eone.submission3.model.source

import androidx.lifecycle.LiveData
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.data.response.ItemListResponse

interface ContentDataSource {
    fun getMovie() : LiveData<List<ItemListResponse>>
    fun getMovieDetail(movieId : Int): LiveData<ItemDetailResponse>
    fun getTvShow() : LiveData<List<ItemListResponse>>
    fun getTvShowDetail(tvShowId: Int) : LiveData<ItemDetailResponse>
}