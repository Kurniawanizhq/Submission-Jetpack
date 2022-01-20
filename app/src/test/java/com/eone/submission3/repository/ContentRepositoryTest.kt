package com.eone.submission3.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.eone.submission3.utils.AppExecutors
import com.eone.submission3.PagedListUtil
import com.eone.submission3.data.local.LocalDataSource
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.model.repository.RemoteDataSource
import com.eone.submission3.utils.FakeDataDummy
import com.eone.submission3.utils.LiveDataTest
import com.eone.submission3.utils.SortUtils
import com.eone.submission3.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class ContentRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteData = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val contentRepository = FakeContentRepositoryTest(remoteData,local,appExecutors)

    private val movieList = FakeDataDummy.getDummyMovie()
    private val movieId = movieList[0].movieId
    private val tvshowList = FakeDataDummy.getDummyTvShow()
    private val tvShowId = tvshowList[0].tvshowId

    private val sort = SortUtils.POPULARITY

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies(sort)).thenReturn(dataSourceFactory)
        contentRepository.getMovie(sort)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.getDummyMovie()))
        verify(local).getMovies(sort)
        assertNotNull(movieEntities.data)
        assertEquals(movieList.size, movieEntities.data?.size)
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows(sort)).thenReturn(dataSourceFactory)
        contentRepository.getTvShow(sort)

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.getDummyTvShow()))
        verify(local).getTvShows(sort)
        assertNotNull(tvshowEntities.data)
        assertEquals(tvshowList.size, tvshowEntities.data?.size)

    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = FakeDataDummy.getDummyMovieDetail()
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovie)


        val movieDetailEntities = LiveDataTest.getValue(contentRepository.getMovieDetail(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieDetailEntities)
        assertEquals(movieId, movieDetailEntities.data?.movieId)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvshow = MutableLiveData<TvShowEntity>()
        dummyTvshow.value = FakeDataDummy.getDummyTvShowDetail()
        `when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyTvshow)


        val tvshowDetailEntities = LiveDataTest.getValue(contentRepository.getTvShowDetail(tvShowId))
        verify(local).getDetailTvShow(tvShowId)
        assertNotNull(tvshowDetailEntities)
        assertEquals(tvShowId, tvshowDetailEntities.data?.tvshowId)
    }

    @Test
    fun getFavoriteMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        contentRepository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.getDummyMovie()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(movieList.size,movieEntity.data?.size)
    }

    @Test
    fun getFavoriteTvshow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        contentRepository.getFavoriteTvShow()

        val tvshowEntity = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.getDummyTvShow()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvshowEntity.data)
        assertEquals(tvshowList.size,tvshowEntity.data?.size)
    }
}