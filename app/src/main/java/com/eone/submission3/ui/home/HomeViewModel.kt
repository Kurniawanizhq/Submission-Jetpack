package com.eone.submission3.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.eone.submission3.model.repository.ContentRepository

class HomeViewModel(private val contentRepository: ContentRepository) : ViewModel() {
     fun getMovies() = contentRepository.getMovie()

    fun getTvShow() = contentRepository.getTvShow()
}