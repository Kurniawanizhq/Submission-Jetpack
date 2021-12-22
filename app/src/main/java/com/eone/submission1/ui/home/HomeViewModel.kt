package com.eone.submission1.ui.home

import androidx.lifecycle.ViewModel
import com.eone.submission1.ContentRepository

class HomeViewModel(private val contentRepository: ContentRepository?) : ViewModel() {
    fun getMovies() = contentRepository?.getMovie()
    fun getTvShow() = contentRepository?.getTvShow()
//    fun getMovies(): ArrayList<DataEntity> = DataDummy.getMovies()
//
//    fun getTvShow(): ArrayList<DataEntity> = DataDummy.getTvShows()
}