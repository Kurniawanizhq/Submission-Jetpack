package com.eone.submission3.model.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.vo.Resource

interface ContentDataSource {
    fun getMovie(sort : String) : LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId : Int): LiveData<Resource<MovieEntity>>
    fun getTvShow(sort : String) : LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowDetail(tvShowId: Int) : LiveData<Resource<TvShowEntity>>
    fun getFavoriteMovies() : LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie (movie : MovieEntity, state : Boolean)
    fun setFavoriteTvShow (tvShow : TvShowEntity, state : Boolean)
    fun getFavoriteTvShow() : LiveData<PagedList<TvShowEntity>>
}