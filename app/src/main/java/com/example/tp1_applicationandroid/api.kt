package com.example.tp1_applicationandroid

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun moviesweek(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbMoviesResult

    @GET("trending/tv/week")
    suspend fun seriesweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String
    ): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun actorsweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String
    ): TmdbActeursResult

    @GET("movie/api_key")
    suspend fun searchMovie(
        @Query("api_key")api_key: String,
        @Query("language") language: String
    ): TmdbSearchResult

    @GET("movie/{id}")
    suspend fun filmDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String
        //@Query("append_to_response") appendToResponse: String = "credits"
    ): Movie

    @GET("tv/{id}")
    suspend fun serieDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String
    ): Series
}
