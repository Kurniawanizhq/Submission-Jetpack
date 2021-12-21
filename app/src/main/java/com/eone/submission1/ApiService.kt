package com.eone.submission1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<ItemResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
        ): Call<ItemDetailResponse>

    @GET("3/tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<ItemResponse>

    @GET("3/tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey : String
    ): Call<ItemDetailResponse>
}