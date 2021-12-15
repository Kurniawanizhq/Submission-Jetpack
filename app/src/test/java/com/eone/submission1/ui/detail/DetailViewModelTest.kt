package com.eone.submission1.ui.detail

import com.eone.submission1.data.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataMovie = DataDummy.getMovies()[0]
    private val dataTvShow = DataDummy.getTvShows()[0]
    private val movieId = dataMovie.id
    private val tvShowId = dataTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMoviesId(movieId)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getDetailMovieById() {
        val movie = viewModel.getDetailMovieById()
        assertNotNull(movie)
        assertEquals(dataMovie.title,movie.title)
        assertEquals(dataMovie.overview,movie.overview)
        assertEquals(dataMovie.duration,movie.duration)
        assertEquals(dataMovie.genre,movie.genre)
        assertEquals(dataMovie.poster,movie.poster)
        assertEquals(dataMovie.background,movie.background)
    }

    @Test
    fun getDetailTvShowById() {
        val tvShow = viewModel.getDetailTvShowById()
        assertNotNull(tvShow)
        assertEquals(dataTvShow.title,tvShow.title)
        assertEquals(dataTvShow.overview,tvShow.overview)
        assertEquals(dataTvShow.duration,tvShow.duration)
        assertEquals(dataTvShow.genre,tvShow.genre)
        assertEquals(dataTvShow.poster,tvShow.poster)
        assertEquals(dataTvShow.background,tvShow.background)
    }
}