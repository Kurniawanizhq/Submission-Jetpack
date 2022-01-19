package com.eone.submission3.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission3.model.repository.ContentRepository

class HomeViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getMovies(sort : String) = contentRepository.getMovie(sort)

    fun getTvShow(sort : String) = contentRepository.getTvShow(sort)
}