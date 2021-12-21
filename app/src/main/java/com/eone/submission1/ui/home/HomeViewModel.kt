package com.eone.submission1.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission1.ApiRepository

class HomeViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun getMovies() = apiRepository.getMovie()
    fun getTvShow() = apiRepository.getTvShow()
//    fun getMovies(): ArrayList<DataEntity> = DataDummy.getMovies()
//
//    fun getTvShow(): ArrayList<DataEntity> = DataDummy.getTvShows()
}