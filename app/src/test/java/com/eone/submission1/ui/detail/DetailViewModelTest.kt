package com.eone.submission1.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eone.submission1.ContentRepository
import com.eone.submission1.FakeDataDummy
import com.eone.submission1.ItemDetailResponse
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

    private val dummyMovieId = FakeDataDummy.getDummyMovieDetail().id
    private val dummyTvShowId = FakeDataDummy.getDummyTvShowDetail().id

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
        movie.value = FakeDataDummy.getDummyMovieDetail()
        Mockito.`when`(contentRepository.getMovieDetail(movie.value!!.id)).thenReturn(movie)
        viewModel.getMovieDetail(movie.value!!.id)?.observeForever(observer)
        Mockito.verify(contentRepository).getMovieDetail(dummyMovieId)

        assertEquals(movie.value!!.backdropPath,viewModel.getMovieDetail(movie.value!!.id)?.value?.backdropPath)
        assertEquals(movie.value!!.posterPath,viewModel.getMovieDetail(movie.value!!.id)?.value?.posterPath)
        assertEquals(movie.value!!.genre,viewModel.getMovieDetail(movie.value!!.id)?.value?.genre)
        assertEquals(movie.value!!.id,viewModel.getMovieDetail(movie.value!!.id)?.value?.id)
        assertEquals(movie.value!!.title,viewModel.getMovieDetail(movie.value!!.id)?.value?.title)
        assertEquals(movie.value!!.duration,viewModel.getMovieDetail(movie.value!!.id)?.value?.duration)
        assertEquals(movie.value!!.overview,viewModel.getMovieDetail(movie.value!!.id)?.value?.overview)
        assertEquals(movie.value!!.releaseMovieDate,viewModel.getMovieDetail(movie.value!!.id)?.value?.releaseMovieDate)
        assertEquals(movie.value!!.voteAverage,viewModel.getMovieDetail(movie.value!!.id)?.value?.voteAverage)
        Mockito.verify(observer).onChanged(movie.value)
    }

    @Test
    fun getDetailTvShowById() {
        val tvShow = MutableLiveData<ItemDetailResponse>()
        tvShow.value = FakeDataDummy.getDummyTvShowDetail()
        Mockito.`when`(contentRepository.getTvShowDetail(tvShow.value!!.id)).thenReturn(tvShow)
        viewModel.getTvShowDetail(tvShow.value!!.id)?.observeForever(observer)
        Mockito.verify(contentRepository).getTvShowDetail(dummyTvShowId)

        assertEquals(tvShow.value!!.backdropPath,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.backdropPath)
        assertEquals(tvShow.value!!.posterPath,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.posterPath)
        assertEquals(tvShow.value!!.genre,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.genre)
        assertEquals(tvShow.value!!.id,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.id)
        assertEquals(tvShow.value!!.name,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.name)
        assertEquals(tvShow.value!!.epsDuration,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.epsDuration)
        assertEquals(tvShow.value!!.overview,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.overview)
        assertEquals(tvShow.value!!.releaseTvDate,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.releaseTvDate)
        assertEquals(tvShow.value!!.voteAverage,viewModel.getTvShowDetail(tvShow.value!!.id)?.value?.voteAverage)
        Mockito.verify(observer).onChanged(tvShow.value)
    }
}