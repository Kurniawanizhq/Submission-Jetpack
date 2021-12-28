package com.eone.submission2.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eone.submission2.model.repository.ContentRepository
import com.eone.submission2.utils.FakeDataDummy
import com.eone.submission2.data.response.ItemListResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<List<ItemListResponse>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(contentRepository)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<ItemListResponse>>()
        val dummyMovies = FakeDataDummy.getDummyMovie()
        movies.value = dummyMovies

        `when`(contentRepository.getMovie()).thenReturn(movies)
        val movie = viewModel.getMovies()?.value
        Mockito.verify(contentRepository).getMovie()
        assertNotNull(movie)
        assertEquals(20,movie?.size)

        viewModel.getMovies()?.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvShow() {
        val tvShows = MutableLiveData<List<ItemListResponse>>()
        val dummyTvShows = FakeDataDummy.getDummyTvShow()
        tvShows.value = dummyTvShows

        `when`(contentRepository.getTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShow()?.value
        Mockito.verify(contentRepository).getTvShow()
        assertNotNull(tvShow)
        assertEquals(20,tvShow?.size)

        viewModel.getTvShow()?.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}