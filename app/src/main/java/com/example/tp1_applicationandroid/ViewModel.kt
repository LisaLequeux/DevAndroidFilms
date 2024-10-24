package com.example.tp1_applicationandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
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
    val acteurs = MutableStateFlow<List<Acteurs>>(listOf())

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

    fun getActeurs(){
        viewModelScope.launch{
            acteurs.value = api.acteursweek(api_key).results
        }
    }
}