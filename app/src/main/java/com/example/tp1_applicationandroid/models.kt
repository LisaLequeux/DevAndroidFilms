package com.example.tp1_applicationandroid

data class TmdbMoviesResult(
    var page: Int = 0,
    val results: List<Movie> = listOf(),
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genres: List<Genre> = listOf(),
    val id: Int,
    //val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

data class TmdbSeriesResult(
    val page: Int = 0,
    val results: List<Series> = listOf(),
    val total_pages: Int,
    val total_results: Int
)

data class Series(
    //val backdrop_path: String,
    val first_air_date: String,
    val genres: List<Genre> = listOf(),
    val id: Int,
    val name: String,
    //val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)

data class TmdbActeursResult(
    val page: Int = 0,
    val results: List<Actors> = listOf(),
    val total_pages: Int,
    val total_results: Int
)

data class Actors(
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val profile_path: String?,
)

data class TmdbSearchResult(
    val page: Int = 0,
    val results: List<Search> = listOf(),
    val total_pages: Int,
    val total_results: Int
)

data class Search(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Genre(
    val id: Int,
    val name: String = ""
)