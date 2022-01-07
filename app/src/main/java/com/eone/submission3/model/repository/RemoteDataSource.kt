package com.eone.submission3.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eone.submission3.ApiResponse
import com.eone.submission3.BuildConfig
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.data.response.ItemResponse
import com.eone.submission3.network.ApiConfig
import com.eone.submission3.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private val apiKey = BuildConfig.API_KEY

    companion object {
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RemoteDataSource()
        }
    }

    fun getMovie(): LiveData<ApiResponse<List<ItemListResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ItemListResponse>>>()
        val client = ApiConfig.getApiServices().getMovies(apiKey)

        client.enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                resultMovies.postValue(ApiResponse.success(response.body()?.result as List<ItemListResponse>))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                resultMovies.postValue(ApiResponse.error(t.message.toString(),mutableListOf()))
                Log.e("RemoteDataSource", "getMovies error : ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultMovies
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<ItemDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<ItemDetailResponse>>()
        val client = ApiConfig.getApiServices().getMovieDetail(movieId, apiKey)

        client.enqueue(object : Callback<ItemDetailResponse> {
            override fun onResponse(
                call: Call<ItemDetailResponse>,
                response: Response<ItemDetailResponse>
            ) {
                resultDetailMovie.postValue(ApiResponse.success(response.body() as ItemDetailResponse))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                resultDetailMovie.postValue(ApiResponse.error(t.message.toString(),
                    ItemDetailResponse("","", listOf(),0,"",0,"null","null",0.0, listOf(),"null","null")
                ))
                Log.e("RemoteDataSource", "getDetailMovies error : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovie
    }

    fun getTvShow(): LiveData<ApiResponse<List<ItemListResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<ItemListResponse>>>()
        val client = ApiConfig.getApiServices().getTvShows(apiKey)

        client.enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                resultTvShows.postValue(ApiResponse.success(response.body()?.result as List<ItemListResponse>))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                resultTvShows.postValue(ApiResponse.error(t.message.toString(), mutableListOf()))
                Log.e("RemoteDataSource", "getTvShow error : ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultTvShows
    }

    fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<ItemDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<ItemDetailResponse>>()
        val client = ApiConfig.getApiServices().getMovieDetail(tvShowId, apiKey)

        client.enqueue(object : Callback<ItemDetailResponse> {
            override fun onResponse(
                call: Call<ItemDetailResponse>,
                response: Response<ItemDetailResponse>
            ) {
                resultDetailTvShow.postValue(ApiResponse.success(response.body() as ItemDetailResponse))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow error : ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultDetailTvShow
    }
}