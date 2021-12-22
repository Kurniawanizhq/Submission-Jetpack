package com.eone.submission1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<ItemResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String
        ): Call<ItemDetailResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<ItemResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey : String
    ): Call<ItemDetailResponse>
}