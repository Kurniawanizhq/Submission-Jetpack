package com.eone.submission1

import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.getMovies()

    fun getTvShow(): List<MovieEntity> = DataDummy.getTvShows()
}