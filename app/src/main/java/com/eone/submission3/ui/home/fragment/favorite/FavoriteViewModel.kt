package com.eone.submission3.ui.home.fragment.favorite

import androidx.lifecycle.ViewModel
import com.eone.submission3.model.repository.ContentRepository

class FavoriteViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getFavoriteMovies () = contentRepository.getFavoriteMovies()

    fun getFavoriteTvshow() = contentRepository.getFavoriteTvShow()
}