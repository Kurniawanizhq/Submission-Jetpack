package com.eone.submission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.vo.Resource

class DetailViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> =
        contentRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> =
        contentRepository.getTvShowDetail(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity, state : Boolean) {
        contentRepository.setFavoriteMovie(movie, state)
    }

    fun setFavoriteTvShow(tvshow: TvShowEntity, state : Boolean) {
        contentRepository.setFavoriteTvShow(tvshow, state)
    }
}