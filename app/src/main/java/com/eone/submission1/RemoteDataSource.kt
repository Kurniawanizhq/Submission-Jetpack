package com.eone.submission1

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource {
    private val apiKey = BuildConfig.apiKey
    private val retrofitConfig = ApiConfig

    companion object {
        private var INSTANCE: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.toString()

        fun getInstance(): RemoteDataSource {
            if (INSTANCE == null)
                INSTANCE = RemoteDataSource()
            return INSTANCE!!
        }
    }

    interface GetMovieCallback {
        fun onResponse(movieResponse: List<ItemDetailResponse>)
    }

    interface GetMovieDetailCallback {
        fun onResponse(movieDetailResponse: ItemDetailResponse)
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<ItemDetailResponse>)
    }

    interface GetTvShowDetailCallback {
        fun onResponse(tvShowDetailResponse: ItemDetailResponse)
    }

    fun getMovie(getMovieCallback: GetMovieCallback) {
        retrofitConfig.getApiServices().getMovies(apiKey).enqueue(object : retrofit2.Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                response.body()?.result?.let {
                    getMovieCallback.onResponse(it)
                }
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
            }

        })
    }

    fun getMovieDetail(movieId: String, getMovieDetailCallback: GetMovieDetailCallback) {
        retrofitConfig.getApiServices().getMovieDetail(movieId, apiKey)
            .enqueue(object : retrofit2.Callback<ItemDetailResponse> {
                override fun onResponse(call: Call<ItemDetailResponse>, response: Response<ItemDetailResponse>) {
                    getMovieDetailCallback.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }
            })
    }

    fun getTvShow(getTvShowCallback: GetTvShowCallback) {
        retrofitConfig.getApiServices().getTvShows(apiKey).enqueue(object : retrofit2.Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                response.body()?.result?.let {
                    getTvShowCallback.onResponse(it)
                }
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
            }

        })
    }

    fun getTvShowDetail(tvShowId : String, getTvShowDetailCallback: GetTvShowDetailCallback){
        retrofitConfig.getApiServices().getTvShowDetail(tvShowId,apiKey).enqueue(object : retrofit2.Callback<ItemDetailResponse>{
            override fun onResponse(call: Call<ItemDetailResponse>, response: Response<ItemDetailResponse>) {
                getTvShowDetailCallback.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                Log.d(TAG,t.printStackTrace().toString())
            }
        })
    }
}