package com.eone.submission1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eone.submission1.ContentRepository
import com.eone.submission1.ItemDetailResponse

class DetailViewModel (private val contentRepository: ContentRepository?): ViewModel() {

    fun getMovieDetail(movieId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getTvShowDetail(tvShowId)
}