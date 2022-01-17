package com.eone.submission3.ui.home

import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity

interface HomeCallback {
    interface OnItemClickedMovie {
        fun onItemClickedMovie(data: MovieEntity)
    }

    interface OnItemClickedTvshow {
        fun onItemClickedTvshow(data: TvShowEntity)
    }
}