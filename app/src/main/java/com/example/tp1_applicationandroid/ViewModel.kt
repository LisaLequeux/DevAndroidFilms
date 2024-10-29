package com.example.tp1_applicationandroid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val api_key="317519a83cc36ab9367ba50e5aa75b40"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        //Même chose que la ligne d'en dessous .create(api::class.java)
    val api = retrofit.create(Api::class.java)
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Series>>(listOf())
    val actors = MutableStateFlow<List<Actors>>(listOf())
    //val searchMovies = MutableStateFlow<List<Movie>>(listOf())

    fun getMovies(){
        viewModelScope.launch {
            movies.value = api.moviesweek(api_key).results
        }
    }

    fun getSeries(){
        viewModelScope.launch{
            series.value = api.seriesweek(api_key).results
        }
    }

    fun getActors(){
        viewModelScope.launch{
            actors.value = api.actorsweek(api_key).results
        }
    }

    fun searchMovie(){
        viewModelScope.launch{
            try {
                movies.value = api.searchMovie(api_key).results
            } catch (e: Exception) {
                Log.e("MainViewModel", "searchMovie: $e")
            }
        }
    }
}
