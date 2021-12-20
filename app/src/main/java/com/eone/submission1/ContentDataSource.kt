package com.eone.submission1

import androidx.lifecycle.LiveData

interface ContentDataSource {
    fun getMovie() : LiveData<List<ItemList>>
    fun getDetailMovie(movieId : String): LiveData<ItemList>
    fun getTvShow() : LiveData<List<ItemList>>
    fun getTvShowDetail(movieId: String) : LiveData<ItemList>
}