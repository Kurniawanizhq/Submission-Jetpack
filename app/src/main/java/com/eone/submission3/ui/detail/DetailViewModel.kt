package com.eone.submission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.vo.Resource

class DetailViewModel (private val contentRepository:ContentRepository): ViewModel() {

    fun getMovieDetail(movieId : Int) : LiveData<Resource<MovieEntity>> = contentRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId : Int) : LiveData<Resource<TvShowEntity>> = contentRepository.getTvShowDetail(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        contentRepository.setFavoriteMovie(movie,false)
    }

    fun setFavoriteTvShow(tvshow: TvShowEntity){
        contentRepository.setFavoriteTvShow(tvshow,false)
    }
}