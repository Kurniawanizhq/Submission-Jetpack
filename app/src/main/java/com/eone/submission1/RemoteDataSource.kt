package com.eone.submission1

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource {
    private val apiKey = API_BASE
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
        fun onResponse(movieResponse: List<ItemList>)
    }

    interface GetMovieDetailCallback {
        fun onResponse(movieDetailResponse: ItemList)
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<ItemList>)
    }

    interface GetTvShowDetailCallback {
        fun onResponse(tvShowDetailResponse: ItemList)
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
            .enqueue(object : retrofit2.Callback<ItemList> {
                override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                    getMovieDetailCallback.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<ItemList>, t: Throwable) {
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
        retrofitConfig.getApiServices().getTvShowDetail(tvShowId,apiKey).enqueue(object : retrofit2.Callback<ItemList>{
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                getTvShowDetailCallback.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                Log.d(TAG,t.printStackTrace().toString())
            }
        })
    }
}