package com.eone.submission3.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.model.source.ContentDataSource

class ContentRepository(private val remoteRepository: RemoteDataSource?) : ContentDataSource {

    companion object {
        @Volatile
        private var INSTANCE: ContentRepository? = null

        fun getInstance(remoteRepository: RemoteDataSource?): ContentRepository? {
            if (INSTANCE == null) {
                synchronized(ContentRepository::class.java) {
                    if (INSTANCE == null) INSTANCE =
                        ContentRepository(
                            remoteRepository
                        )
                }
            }
            return INSTANCE
        }
    }

    override fun getMovie(): LiveData<List<ItemListResponse>> {
        val listMovie = MutableLiveData<List<ItemListResponse>>()
        remoteRepository?.getMovie(object :
            RemoteDataSource.GetMovieCallback {
            override fun onResponse(movieResponse: List<ItemListResponse>?) {
                listMovie.postValue(movieResponse)
            }
        })
        return listMovie
    }


    override fun getMovieDetail(movieId : Int): LiveData<ItemDetailResponse> {
        val movieDetail = MutableLiveData<ItemDetailResponse>()
        remoteRepository?.getMovieDetail(movieId,object :
            RemoteDataSource.GetMovieDetailCallback {
            override fun onResponse(movieDetailResponse: ItemDetailResponse?) {
                movieDetail.postValue(movieDetailResponse)
            }
        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<ItemListResponse>> {
        val listTvShow = MutableLiveData<List<ItemListResponse>>()
        remoteRepository?.getTvShow(object :
            RemoteDataSource.GetTvShowCallback {
            override fun onResponse(tvShowResponse: List<ItemListResponse>?) {
                listTvShow.postValue(tvShowResponse)
            }
        })
        return listTvShow
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<ItemDetailResponse> {
        val tvShowDetail = MutableLiveData<ItemDetailResponse>()
        remoteRepository?.getTvShowDetail(tvShowId,object :
            RemoteDataSource.GetTvShowDetailCallback {
            override fun onResponse(tvShowDetailResponse: ItemDetailResponse?) {
                tvShowDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvShowDetail
    }
}
