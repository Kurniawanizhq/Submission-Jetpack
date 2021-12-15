package com.eone.submission1.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission1.data.DataDummy
import com.eone.submission1.model.DataEntity

class HomeViewModel : ViewModel() {
    fun getMovies(): ArrayList<DataEntity> = DataDummy.getMovies()

    fun getTvShow(): ArrayList<DataEntity> = DataDummy.getTvShows()
}