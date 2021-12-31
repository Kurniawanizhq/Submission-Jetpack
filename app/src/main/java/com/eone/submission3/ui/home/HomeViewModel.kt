package com.eone.submission3.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission3.model.repository.ContentRepository

class HomeViewModel(private val contentRepository: ContentRepository?) : ViewModel() {
    fun getMovies() = contentRepository?.getMovie()

    fun getTvShow() = contentRepository?.getTvShow()
}