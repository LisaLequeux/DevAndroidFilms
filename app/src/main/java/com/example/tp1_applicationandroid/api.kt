package com.example.tp1_applicationandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun moviesweek(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbMoviesResult

    @GET("trending/tv/week")
    suspend fun seriesweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun acteursweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbActeursResult

    @GET("movie/api_key")
    suspend fun searchMovie(
        @Query("api_key")api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbMoviesResult
}
