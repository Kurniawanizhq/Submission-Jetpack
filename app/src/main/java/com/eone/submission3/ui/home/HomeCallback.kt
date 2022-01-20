package com.eone.submission3.ui.home

import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity

interface HomeCallback {
    interface OnItemClickedMovie {
        fun onItemClickedMovie(data: MovieEntity)
    }

    interface OnItemClickedTvshow {
        fun onItemClickedTvshow(data: TvShowEntity)
    }
}