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

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
    ): TmdbMoviesResult

    @GET("movie/{id}")
    suspend fun filmDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String
    ): Movie

    @GET("trending/tv/week")
    suspend fun seriesweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String
    ): TmdbSeriesResult

    @GET("tv/{id}")
    suspend fun serieDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String
    ): Series

    @GET("search/tv")
    suspend fun searchSerie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
    ): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun actorsweek(
        @Query("api_key")api_key: String,
        @Query("language") language: String
    ): TmdbActeursResult

    @GET("person/{id}")
    suspend fun actorDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String
    ): Actors

    @GET("search/person")
suspend fun searchActeur(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
    ): TmdbActeursResult

}
