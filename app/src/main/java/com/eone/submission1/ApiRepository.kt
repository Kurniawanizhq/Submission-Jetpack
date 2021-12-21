package com.eone.submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eone.submission1.model.DataEntity

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

    override fun getMovie(): LiveData<List<DataEntity>> {
        val listMovie = MutableLiveData<List<DataEntity>>()
        remoteRepository.getMovie(object : RemoteDataSource.GetMovieCallback{
            override fun onResponse(movieResponse: List<DataEntity>) {
                listMovie.postValue(movieResponse)
            }

            override fun onResponse(movieResponse: List<ItemResponse>) {
                TODO("Not yet implemented")
            }
        })
        return listMovie
    }


    override fun getDetailMovie(movieId : String): LiveData<DataEntity> {
        val movieDetail =MutableLiveData<DataEntity>()
        remoteRepository.getMovieDetail(movieId,object : RemoteDataSource.GetMovieDetailCallback{
            override fun onResponse(movieDetailResponse: DataEntity) {
                movieDetail.postValue(movieDetailResponse)
            }
        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<DataEntity>> {
        val listTvShow = MutableLiveData<List<DataEntity>>()
        remoteRepository.getTvShow(object : RemoteDataSource.GetTvShowCallback{
            override fun onResponse(tvShowResponse: List<DataEntity>) {
                listTvShow.postValue(tvShowResponse)
            }
        })
        return listTvShow
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<DataEntity> {
        val tvShowDetail = MutableLiveData<DataEntity>()
        remoteRepository.getTvShowDetail(tvShowId,object : RemoteDataSource.GetTvShowDetailCallback{
            override fun onResponse(tvShowDetailResponse: DataEntity) {
                tvShowDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvShowDetail
    }
}
