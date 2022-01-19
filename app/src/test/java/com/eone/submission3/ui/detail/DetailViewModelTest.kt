package com.eone.submission3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.utils.FakeDataDummy
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.ui.detail.DetailViewModel
import com.eone.submission3.vo.Resource
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
    private val dummyMovieId = dummyMovie.movieId

    private val dummyTvShow = FakeDataDummy.getDummyTvShowDetail()
    private val dummyTvShowId = dummyTvShow.tvshowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var  tvObserver: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(contentRepository)
    }

    @Test
    fun getDetailMovieById() {
        val dummyDetail = Resource.success(FakeDataDummy.getDummyMovieDetail())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetail

        Mockito.`when`(contentRepository.getMovieDetail(dummyMovieId)).thenReturn(movie)
        val detailEntity = viewModel.getMovieDetail(dummyMovieId).value?.data

        assertNotNull(detailEntity)
        assertEquals(dummyMovie.backdropPath,detailEntity?.backdropPath)
        assertEquals(dummyMovie.posterPath,detailEntity?.posterPath)
        assertEquals(dummyMovie.genre,detailEntity?.genre)
        assertEquals(dummyMovie.movieId,detailEntity?.movieId)
        assertEquals(dummyMovie.title,detailEntity?.title)
        assertEquals(dummyMovie.duration,detailEntity?.duration)
        assertEquals(dummyMovie.overview,detailEntity?.overview)
        assertEquals(dummyMovie.releaseDate,detailEntity?.releaseDate)
        assertEquals (dummyMovie.voteAverage,detailEntity!!.voteAverage,0.1)

        viewModel.getMovieDetail(dummyMovieId).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyDetail)
    }

    @Test
    fun getDetailTvShowById() {
        val dummyDetail = Resource.success(FakeDataDummy.getDummyTvShowDetail())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetail

        Mockito.`when`(contentRepository.getTvShowDetail(dummyTvShowId)).thenReturn(tvShow)
        val detailTvShow = viewModel.getTvShowDetail(dummyTvShowId).value?.data

        assertNotNull(detailTvShow)


        assertEquals(dummyTvShow.backdropPath,detailTvShow?.backdropPath)
        assertEquals(dummyTvShow.posterPath,detailTvShow?.posterPath)
        assertEquals(dummyTvShow.genre,detailTvShow?.genre)
        assertEquals(dummyTvShow.tvshowId,detailTvShow?.tvshowId)
        assertEquals(dummyTvShow.name,detailTvShow?.name)
        assertEquals(dummyTvShow.duration,detailTvShow?.duration)
        assertEquals(dummyTvShow.overview,detailTvShow?.overview)
        assertEquals(dummyTvShow.releaseDate,detailTvShow?.releaseDate)
        assertEquals(dummyTvShow.voteAverage,detailTvShow!!.voteAverage, 0.1)

        viewModel.getTvShowDetail(dummyTvShowId).observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyDetail)
    }
}