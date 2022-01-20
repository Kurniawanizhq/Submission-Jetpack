package com.eone.submission3.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.utils.SortUtils
import com.eone.submission3.vo.Resource
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
    private  var sort = SortUtils.POPULARITY

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvshowObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvshowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(contentRepository)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val dummyMovies = Resource.success(moviePagedList)
        `when`(dummyMovies.data?.size).thenReturn(4)
        movies.value = dummyMovies

        `when`(contentRepository.getMovie(sort)).thenReturn(movies)
        val movie = viewModel.getMovies(sort).value?.data
        Mockito.verify(contentRepository).getMovie(sort)
        assertNotNull(movie)
        assertEquals(4,movie?.size)

        viewModel.getMovies(sort).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTvShow() {
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val dummyTvShows = Resource.success(tvshowPagedList)
        `when`(dummyTvShows.data?.size).thenReturn(4)
        tvShows.value = dummyTvShows

        `when`(contentRepository.getTvShow(sort)).thenReturn(tvShows)
        val tvShow = viewModel.getTvShow(sort).value?.data
        Mockito.verify(contentRepository).getTvShow(sort)
        assertNotNull(tvShow)
        assertEquals(4,tvShow?.size)

        viewModel.getTvShow(sort).observeForever(tvshowObserver)
        Mockito.verify(tvshowObserver).onChanged(dummyTvShows)
    }
}