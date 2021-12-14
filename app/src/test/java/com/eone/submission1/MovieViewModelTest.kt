package com.eone.submission1

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @org.junit.Test
    fun getMovies() {
        val dataEntity = viewModel.getMovies()
        assertNotNull(dataEntity)
        assertEquals(10, dataEntity.size)
    }

    @org.junit.Test
    fun getTvShow() {
        val dataEntity = viewModel.getTvShow()
        assertNotNull(dataEntity)
        assertEquals(10, dataEntity.size)
    }
}