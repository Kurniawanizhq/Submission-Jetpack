package com.eone.submission1.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eone.submission1.ContentRepository
import com.eone.submission1.FakeDataDummy
import com.eone.submission1.ItemListResponse
import com.eone.submission1.data.DataDummy
import com.eone.submission1.model.DataEntity
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
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var  observer: Observer<List<ItemListResponse>>

    @Mock
    val content = MutableLiveData<List<ItemListResponse>>()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(contentRepository)
    }

    @Test
    fun getMovies() {
        content.value = FakeDataDummy.getDummyMovie()

        `when`(contentRepository.getMovie()).thenReturn(content)
        viewModel.getMovies()?.observeForever(observer)
        Mockito.verify(contentRepository).getMovie()
    }

    @Test
    fun getTvShow() {
        content.value = FakeDataDummy.getDummyTvShow()
        `when`(contentRepository.getTvShow()).thenReturn(content)
        viewModel.getTvShow()?.observeForever(observer)
        Mockito.verify(contentRepository).getTvShow()
    }
}