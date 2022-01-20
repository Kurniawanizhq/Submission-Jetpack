package com.eone.submission3.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.lifecycle.Observer
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.model.repository.ContentRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel : FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteViewModel(contentRepository)
    }

    @Test
    fun getFavoriteMovie(){
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(4)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(contentRepository.getFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovies().value
        verify(contentRepository).getFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(4,movieEntity?.size)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getFavoriteTvshow(){
        val dummyTvShow = tvShowPagedList
        `when`(dummyTvShow.size).thenReturn(4)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(contentRepository.getFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getFavoriteTvshow().value
        verify(contentRepository).getFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(4,tvShowEntity?.size)

        viewModel.getFavoriteTvshow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

}