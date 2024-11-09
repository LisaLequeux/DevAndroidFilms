package com.example.tp1_applicationandroid

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {

    val api_key="317519a83cc36ab9367ba50e5aa75b40"
    private val moshi = Moshi.Builder() // adapter
        .add(KotlinJsonAdapterFactory())
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    //Même chose que la ligne d'en dessous .create(api::class.java)
    val api = retrofit.create(Api::class.java)


    //val movies = MutableStateFlow<List<Movie>>(listOf())
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies
    val series = MutableStateFlow<List<Series>>(listOf())
    val actors = MutableStateFlow<List<Actors>>(listOf())
    val searchMovies = MutableStateFlow<List<Movie>>(listOf())
    //private val _searchMovies = MutableStateFlow<List<Movie>>(emptyList())
    //val searchMovies: StateFlow<List<Movie>> = _searchMovies

    fun getMovies(language: String = "fr"){
        viewModelScope.launch {
            _movies.value = api.moviesweek(api_key, language).results
        }
    }

    fun getFilmDetails(filmId: String, language: String = "fr") {
        viewModelScope.launch {
            val movieDetails = api.filmDetails(filmId, api_key, language)
            _movies.value = listOf(movieDetails)
        }
    }

    fun searchMovies(searchQuery: String){
        viewModelScope.launch{
            try {
                searchMovies.value = api.searchMovie(api_key, searchQuery).results
                //val result = api.searchMovie(api_key, searchQuery, language).results
                //_searchMovies.value = listOf(result)
            } catch (e: Exception) {
                Log.e("MainViewModel", "searchMovie: $e")
            }
        }
    }

    fun getSeries(language: String = "fr"){
        viewModelScope.launch{
            series.value = api.seriesweek(api_key, language).results
        }
    }

    fun getSerieDetails(serieId: String, language: String = "fr") {
        viewModelScope.launch {
            val serieDetails = api.serieDetails(serieId, api_key, language)
            series.value = listOf(serieDetails)
        }
    }

    fun getActors(language: String = "fr"){
        viewModelScope.launch{
            actors.value = api.actorsweek(api_key, language).results
        }
    }


}
