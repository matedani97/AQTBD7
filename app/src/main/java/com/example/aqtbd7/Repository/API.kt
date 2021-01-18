package com.example.aqtbd7.Repository

import com.example.aqtbd7.Models.Details
import com.example.aqtbd7.Models.MovieDbResponse
import com.example.aqtbd7.Models.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
const val APIKEY="9200afa063c175d4c9f2ce976a02b9da"

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = APIKEY,
        @Query("page") page: Int
    ): Call<MovieDbResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = APIKEY,
        @Query("page") page: Int
    ): Call<MovieDbResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = APIKEY,
        @Query("page") page: Int
    ): Call<MovieDbResponse>

    @GET("movie/{movie_id}")
    fun getMovieDerails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = APIKEY
    ): Call<Details>
}
