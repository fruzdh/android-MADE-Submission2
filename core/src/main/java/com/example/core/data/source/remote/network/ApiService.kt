package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import com.example.core.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(@Query("api_key") api_key: String): ListMovieResponse

    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("api_key") api_key: String): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(@Query("api_key") api_key: String): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(@Query("api_key") api_key: String): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String
    ): MovieDetailResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String
    ): ListMovieResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getMovieSimilar(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String
    ): ListMovieResponse

    @GET("search/movie")
    suspend fun getMovieSearch(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): ListMovieResponse
}