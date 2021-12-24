package com.eone.submission1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteData = mock(RemoteDataSource::class.java)
    private val contentRepository = FakeContentRepository(remoteData)

    private val movieListResponse = FakeDataDummy.getDummyMovie()
    private val movieId = movieListResponse[0].id
    private val tvShowListResponse = FakeDataDummy.getDummyTvShow()
    private val tvShowId = tvShowListResponse[0].id

    private val movieDetailResponse = FakeDataDummy.getDummyMovieDetail()
    private val tvShowDetailResponse = FakeDataDummy.getDummyTvShowDetail()

    private fun <T> anyOfT(type: Class<T>): T = any(type)

        private fun <T> eqOfT(obj: T): T = eq(obj)

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.GetMovieCallback).onResponse(movieListResponse)
            null
        }.`when`(remoteData).getMovie(anyOfT(RemoteDataSource.GetMovieCallback::class.java))

        val movieEntities = LiveDataTest.getValue(contentRepository.getMovie())
        verify(remoteData).getMovie(anyOfT(RemoteDataSource.GetMovieCallback::class.java))
        assertNotNull(movieEntities)
        assertEquals(movieListResponse.size, movieEntities.size)
    }

    @Test
    fun getTvShow() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.GetTvShowCallback).onResponse(tvShowListResponse)
            null
        }.`when`(remoteData).getTvShow(anyOfT(RemoteDataSource.GetTvShowCallback::class.java))

        val tvShowEntities = LiveDataTest.getValue(contentRepository.getTvShow())
        verify(remoteData).getTvShow(anyOfT(RemoteDataSource.GetTvShowCallback::class.java))
        assertNotNull(tvShowEntities)
        assertEquals(tvShowListResponse.size, tvShowEntities.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.GetMovieDetailCallback).onResponse(
                movieDetailResponse
            )
            null
        }.`when`(remoteData).getMovieDetail(eqOfT(movieId), anyOfT(RemoteDataSource.GetMovieDetailCallback::class.java))

        val movieDetailEntities = LiveDataTest.getValue(contentRepository.getMovieDetail(eqOfT(movieId)))
        verify(remoteData).getMovieDetail(
            eqOfT(movieId),
            anyOfT(RemoteDataSource.GetMovieDetailCallback::class.java)
        )
        assertNotNull(movieDetailEntities)
        assertEquals(movieDetailResponse.id,movieDetailEntities.id)
    }
}