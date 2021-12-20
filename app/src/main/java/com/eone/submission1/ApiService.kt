package com.eone.submission1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getMovies(): Call<ItemResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
    ): Call<ItemList>

    @GET("3/tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<ItemResponse>

    @GET("3/tv/{tv_id}")
    fun getTvShowDetail(
        @Query("query") title: String
    ): Call<Tv>
}