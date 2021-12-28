package com.eone.submission2.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission2.model.repository.ContentRepository

class HomeViewModel(private val contentRepository: ContentRepository?) : ViewModel() {
    fun getMovies() = contentRepository?.getMovie()

    fun getTvShow() = contentRepository?.getTvShow()
}