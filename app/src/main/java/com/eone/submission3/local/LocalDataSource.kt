package com.eone.submission3.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.eone.submission3.ContentDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val contentDao: ContentDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(contentDao)
    }

    fun getMovies(): PagingSource<Int, MovieEntity> =contentDao.getMovies()

    fun getTvShows(): PagingSource<Int, TvShowEntity> = contentDao.getTvShow()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = contentDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> = contentDao.getDetailTvShowById(tvShowId)

    fun getFavoriteMovies(): PagingSource<Int, MovieEntity> = contentDao.getFavoriteMovies()

    fun getFavoriteTvShow(): PagingSource<Int, TvShowEntity> = contentDao.getFavoriteTvShow()

    fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun insertTvShow(tvShows: List<TvShowEntity>) = contentDao.insertTvShow(tvShows)


    fun setFavoriteMovie(movieEntity: MovieEntity, state : Boolean) {
        movieEntity.isFavorite = !state
        contentDao.updateMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state : Boolean) {
        tvShowEntity.isFavorite = !state
        contentDao.updateTvShow(tvShowEntity)
    }

}