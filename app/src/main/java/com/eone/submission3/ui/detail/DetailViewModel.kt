package com.eone.submission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.data.response.ItemDetailResponse

class DetailViewModel (private val contentRepository:ContentRepository?): ViewModel() {

    fun getMovieDetail(movieId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getTvShowDetail(tvShowId)
}