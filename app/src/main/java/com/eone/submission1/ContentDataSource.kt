package com.eone.submission1

import androidx.lifecycle.LiveData
import com.eone.submission1.model.DataEntity

interface ContentDataSource {
    fun getMovie() : LiveData<List<ItemListResponse>>
    fun getMovieDetail(movieId : Int): LiveData<ItemDetailResponse>
    fun getTvShow() : LiveData<List<ItemListResponse>>
    fun getTvShowDetail(tvShowId: Int) : LiveData<ItemDetailResponse>
    fun getMoviesGenre() : LiveData<List<Genre>>
    fun getTvShowGenre() : LiveData<List<Genre>>
}