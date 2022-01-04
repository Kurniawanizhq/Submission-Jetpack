package com.eone.submission3.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.eone.submission3.ContentDao

class LocalDataSource(private val contentDao: ContentDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(contentDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getMovies()

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = contentDao.getTvShow()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = contentDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> =
        contentDao.getDetailTvShowById(tvShowId)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getFavoriteMovies()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = contentDao.getFavoriteTvShow()

    fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun insertTvShow(tvShows: List<TvShowEntity>) = contentDao.insertTvShow(tvShows)

    fun setFavoriteMovie(movieEntity: MovieEntity, state : Boolean) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        contentDao.updateMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state : Boolean) {
        tvShowEntity.isFavorite = !tvShowEntity.isFavorite
        contentDao.updateTvShow(tvShowEntity)
    }
}