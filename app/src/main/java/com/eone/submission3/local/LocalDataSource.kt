package com.eone.submission3.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.eone.submission3.ContentDao
import com.eone.submission3.utils.SortUtils

class LocalDataSource (private val contentDao: ContentDao) {

    fun getMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return contentDao.getMovies(query)
    }

    fun getTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> {
        val query = SortUtils.getSortedQueryTvShow(sort)
        return contentDao.getTvShow(query)
    }

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = contentDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> =
        contentDao.getDetailTvShowById(tvShowId)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getFavoriteMovies()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = contentDao.getFavoriteTvShow()

    fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun insertTvShow(tvShows: List<TvShowEntity>) = contentDao.insertTvShow(tvShows)


    fun setFavoriteMovie(movieEntity: MovieEntity, state: Boolean) {
        movieEntity.isFavorite = !state
        contentDao.updateMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state: Boolean) {
        tvShowEntity.isFavorite = !state
        contentDao.updateTvShow(tvShowEntity)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(contentDao)
    }
}