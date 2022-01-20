package com.eone.submission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.model.repository.ContentRepository

class FavoriteViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getFavoriteMovies() = contentRepository.getFavoriteMovies()

    fun getFavoriteTvshow() = contentRepository.getFavoriteTvShow()
}