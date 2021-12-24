package com.eone.submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeContentRepository(private val remoteRepository: RemoteDataSource?) : ContentDataSource {

    override fun getMovie(): LiveData<List<ItemListResponse>> {
        val listMovie = MutableLiveData<List<ItemListResponse>>()
        remoteRepository?.getMovie(object : RemoteDataSource.GetMovieCallback{
            override fun onResponse(movieResponse: List<ItemListResponse>) {
                listMovie.postValue(movieResponse)
            }
        })
        return listMovie
    }


    override fun getMovieDetail(movieId : Int): LiveData<ItemDetailResponse> {
        val movieDetail = MutableLiveData<ItemDetailResponse>()
        remoteRepository?.getMovieDetail(movieId,object : RemoteDataSource.GetMovieDetailCallback{

            override fun onResponse(movieDetailResponse: ItemDetailResponse) {
                movieDetail.postValue(movieDetailResponse)

            }
        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<ItemListResponse>> {
        val listTvShow = MutableLiveData<List<ItemListResponse>>()
        remoteRepository?.getTvShow(object : RemoteDataSource.GetTvShowCallback{
            override fun onResponse(tvShowResponse: List<ItemListResponse>) {
                listTvShow.postValue(tvShowResponse)
            }
        })
        return listTvShow
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<ItemDetailResponse> {
        val tvShowDetail = MutableLiveData<ItemDetailResponse>()
        remoteRepository?.getTvShowDetail(tvShowId,object : RemoteDataSource.GetTvShowDetailCallback{
            override fun onResponse(tvShowDetailResponse: ItemDetailResponse) {
                tvShowDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvShowDetail
    }
}
