package com.eone.submission3

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM tab_movie")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tab_tvshow")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tab_movie WHERE movie_id = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tab_tvshow WHERE tvshow_id = :tvshowId and id = null")
    fun getDetailTvShowById(tvshowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(tvshows : List<TvShowEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie : MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTvShow(tvshow: TvShowEntity)

    @Query("SELECT * FROM tab_movie WHERE is_favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tab_tvshow WHERE is_favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>
}