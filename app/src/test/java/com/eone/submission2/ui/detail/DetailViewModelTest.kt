package com.eone.submission2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eone.submission2.model.repository.ContentRepository
import com.eone.submission2.utils.FakeDataDummy
import com.eone.submission2.data.response.ItemDetailResponse
import com.eone.submission2.ui.detail.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = FakeDataDummy.getDummyMovieDetail()
    private val dummyMovieId = dummyMovie.id

    private val dummyTvShow = FakeDataDummy.getDummyTvShowDetail()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  observer: Observer<ItemDetailResponse>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(contentRepository)
    }

    @Test
    fun getDetailMovieById() {
        val movie = MutableLiveData<ItemDetailResponse>()
        movie.value = dummyMovie

        Mockito.`when`(contentRepository.getMovieDetail(movie.value!!.id)).thenReturn(movie)
        val detailEntity = viewModel.getMovieDetail(dummyMovieId)?.value as ItemDetailResponse

        assertNotNull(detailEntity)
        assertEquals(dummyMovie.backdropPath,detailEntity.backdropPath)
        assertEquals(dummyMovie.posterPath,detailEntity.posterPath)
        assertEquals(dummyMovie.genre,detailEntity.genre)
        assertEquals(dummyMovie.id,detailEntity.id)
        assertEquals(dummyMovie.title,detailEntity.title)
        assertEquals(dummyMovie.duration,detailEntity.duration)
        assertEquals(dummyMovie.overview,detailEntity.overview)
        assertEquals(dummyMovie.releaseMovieDate,detailEntity.releaseMovieDate)
        assertEquals (dummyMovie.voteAverage,detailEntity.voteAverage,0.1)

        viewModel.getMovieDetail(dummyMovieId)?.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTvShowById() {
        val tvShow = MutableLiveData<ItemDetailResponse>()
        tvShow.value = dummyTvShow

        Mockito.`when`(contentRepository.getTvShowDetail(dummyTvShowId)).thenReturn(tvShow)
        val detailTvShow = viewModel.getTvShowDetail(dummyTvShowId)?.value  as ItemDetailResponse

        assertNotNull(detailTvShow)


        assertEquals(dummyTvShow.backdropPath,detailTvShow.backdropPath)
        assertEquals(dummyTvShow.posterPath,detailTvShow.posterPath)
        assertEquals(dummyTvShow.genre,detailTvShow.genre)
        assertEquals(dummyTvShow.id,detailTvShow.id)
        assertEquals(dummyTvShow.name,detailTvShow.name)
        assertEquals(dummyTvShow.epsDuration,detailTvShow.epsDuration)
        assertEquals(dummyTvShow.overview,detailTvShow.overview)
        assertEquals(dummyTvShow.releaseTvDate,detailTvShow.releaseTvDate)
        assertEquals(dummyTvShow.voteAverage,detailTvShow.voteAverage, 0.1)

        viewModel.getTvShowDetail(dummyTvShowId)?.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}