package com.eone.submission3.model.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.vo.Resource

interface ContentDataSource {
    fun getMovie() : LiveData<Resource<PagingData<MovieEntity>>>
    fun getMovieDetail(movieId : Int): LiveData<Resource<MovieEntity>>
    fun getTvShow() : LiveData<Resource<PagingData<TvShowEntity>>>
    fun getTvShowDetail(tvShowId: Int) : LiveData<Resource<TvShowEntity>>
    fun getFavoriteMovies() : LiveData<PagingData<MovieEntity>>
    fun setFavoriteMovie (movie : MovieEntity, state : Boolean)
    fun setFavoriteTvShow (tvShow : TvShowEntity, state : Boolean)
    fun getFavoriteTvShow() : LiveData<PagingData<TvShowEntity>>
}