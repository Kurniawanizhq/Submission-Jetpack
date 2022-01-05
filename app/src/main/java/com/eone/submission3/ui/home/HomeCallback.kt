package com.eone.submission3.ui.home

import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity

interface HomeCallback {
    fun onItemClickedMovie(data: MovieEntity)

    fun onItemClickedTvshow(data : TvShowEntity)

}