package com.eone.submission2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eone.submission2.model.repository.ContentRepository
import com.eone.submission2.data.response.ItemDetailResponse

class DetailViewModel (private val contentRepository: ContentRepository?): ViewModel() {

    fun getMovieDetail(movieId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId : Int) : LiveData<ItemDetailResponse>? = contentRepository?.getTvShowDetail(tvShowId)
}