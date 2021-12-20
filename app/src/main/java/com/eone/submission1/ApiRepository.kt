package com.eone.submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ApiRepository(private val remoteRepository: RemoteDataSource) : ContentDataSource {

    companion object {
        @Volatile
        private var INSTANCE: ApiRepository? = null

        fun getInstance(remoteRepository: RemoteDataSource): ApiRepository? {
            if (INSTANCE == null) {
                synchronized(ApiRepository::class.java) {
                    if (INSTANCE == null) INSTANCE = ApiRepository(remoteRepository)
                }
            }
            return INSTANCE
        }
    }

    override fun getMovie(): LiveData<List<ItemList>> {
        val listMovie = MutableLiveData<List<ItemList>>()
        remoteRepository.getMovie(object : RemoteDataSource.GetMovieCallback{
            override fun onResponse(movieResponse: List<ItemList>) {
                listMovie.postValue(movieResponse)
            }
        })
        return listMovie
    }


    override fun getDetailMovie(movieId : String): LiveData<ItemList> {
        val movieDetail =MutableLiveData<ItemList>()
        remoteRepository.getMovieDetail(movieId,object : RemoteDataSource.GetMovieDetailCallback{
            override fun onResponse(movieDetailResponse: ItemList) {
                movieDetail.postValue(movieDetailResponse)
            }
        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<ItemList>> {
        val listTvShow = MutableLiveData<List<ItemList>>()
        remoteRepository.getTvShow(object : RemoteDataSource.GetTvShowCallback{
            override fun onResponse(tvShowResponse: List<ItemList>) {
                listTvShow.postValue(tvShowResponse)
            }
        })
        return listTvShow
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<ItemList> {
        val tvShowDetail = MutableLiveData<ItemList>()
        remoteRepository.getTvShowDetail(tvShowId,object : RemoteDataSource.GetTvShowDetailCallback{
            override fun onResponse(tvShowDetailResponse: ItemList) {
                tvShowDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvShowDetail
    }
}
