package com.eone.submission1.ui.detail

import androidx.lifecycle.ViewModel
import com.eone.submission1.data.DataDummy
import com.eone.submission1.model.DataEntity

class DetailViewModel : ViewModel() {

    private lateinit var movieId : String
    private lateinit var tvShowId : String

    fun getDetailMovieById(): DataEntity {
        lateinit var result: DataEntity
        val listMovie = DataDummy.getMovies()
        for (movie in listMovie) {
            if (movie.id == movieId) {
                result = movie
                break
            }
        }
        return result
    }

    fun getDetailTvShowById(): DataEntity {
        lateinit var result: DataEntity
        val listTvShow = DataDummy.getTvShows()
        for (tvShow in listTvShow) {
            if (tvShow.id == tvShowId) {
                result = tvShow
                break
            }
        }
        return result
    }

    fun setMoviesId(id: String) {
        this.movieId = id
    }

    fun setTvShowId(id: String) {
        this.tvShowId = id
    }

}