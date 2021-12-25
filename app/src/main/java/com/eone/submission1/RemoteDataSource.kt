package com.eone.submission1

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private val apiKey = BuildConfig.API_KEY
    private val retrofitConfig = ApiConfig

    companion object {
        private var INSTANCE: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.toString()

        fun getInstance(): RemoteDataSource? {
            if (INSTANCE == null)
                INSTANCE = RemoteDataSource()
            return INSTANCE
        }
    }

    interface GetMoviesGenreCallback{
        fun onResponse(movieGenreResponse : List<Genre>)
    }

    interface GetTvShowGenreCallback{
        fun onResponse(tvShowGenreResponse : List<Genre>)
    }

    interface GetMovieCallback {
        fun onResponse(movieResponse: List<ItemListResponse>)
    }

    interface GetMovieDetailCallback {
        fun onResponse(movieDetailResponse: ItemDetailResponse)
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<ItemListResponse>)
    }

    interface GetTvShowDetailCallback {
        fun onResponse(tvShowDetailResponse: ItemDetailResponse)
    }

    fun getMovieGenre(getMoviesGenreCallback: GetMoviesGenreCallback){
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getMoviesGenre(apiKey).enqueue(object : Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                response.body()?.genres?.let {
                    getMoviesGenreCallback.onResponse(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getTvShowGenre(getTvShowGenreCallback: GetTvShowGenreCallback){
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getTvShowsGenre(apiKey).enqueue(object  : Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                response.body()?.genres?.let {
                    getTvShowGenreCallback.onResponse(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getMovie(getMovieCallback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getMovies(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                response.body()?.result?.let {
                    getMovieCallback.onResponse(it)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovieDetail(movieId: Int, getMovieDetailCallback: GetMovieDetailCallback) {
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getMovieDetail(movieId, apiKey)
            .enqueue(object : Callback<ItemDetailResponse> {
                override fun onResponse(call: Call<ItemDetailResponse>, response: Response<ItemDetailResponse>) {
                    getMovieDetailCallback.onResponse(response.body()!!)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getTvShow(getTvShowCallback: GetTvShowCallback) {
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getTvShows(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                response.body()?.result?.let {
                    getTvShowCallback.onResponse(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getTvShowDetail(tvShowId : Int, getTvShowDetailCallback: GetTvShowDetailCallback){
        EspressoIdlingResource.increment()
        retrofitConfig.getApiServices().getTvShowDetail(tvShowId,apiKey).enqueue(object : Callback<ItemDetailResponse>{
            override fun onResponse(call: Call<ItemDetailResponse>, response: Response<ItemDetailResponse>) {
                getTvShowDetailCallback.onResponse(response.body()!!)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemDetailResponse>, t: Throwable) {
                Log.d(TAG,t.printStackTrace().toString())
                EspressoIdlingResource.decrement()
            }
        })
    }
}