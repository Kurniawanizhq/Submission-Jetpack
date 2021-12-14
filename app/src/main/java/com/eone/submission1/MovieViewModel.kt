package com.eone.submission1

import androidx.lifecycle.ViewModel
import com.eone.submission1.data.DataDummy
import com.eone.submission1.model.DataEntity

class MovieViewModel : ViewModel() {
    fun getMovies(): List<DataEntity> = DataDummy.getMovies()

    fun getTvShow(): List<DataEntity> = DataDummy.getTvShows()
}