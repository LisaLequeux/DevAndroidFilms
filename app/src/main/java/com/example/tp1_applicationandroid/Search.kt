package com.example.tp1_applicationandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun Search (viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.searchMovie()
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        Presentation(
            name = "Android",
        )
        RappelRecherche(
            name = "Android",
        )
        Column {
            CadreFilms(
                name = "Android",
                modifier = Modifier.align(Alignment.Start)
            )
            //Afficher une LazyHorizontalGrid avec les resultats de films recherchés
            val searchMovies by viewModel.movies.collectAsStateWithLifecycle()

            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                modifier = Modifier.padding(5.dp).fillMaxWidth()
            ) {
                items(searchMovies.size) { index ->
                    val searchMovie = searchMovies[index]
                    Card(
                        modifier = Modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 12.dp,
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${searchMovie.poster_path}",
                                contentDescription = "poster",
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                searchMovie.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                searchMovie.release_date,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        //Afficher une LazyHorizontalGrid avec les resultats de séries recherchées
        //Afficher une LazyHorizontalGrid avec les resultats d'acteurs recherchés

    }
}

@Composable
fun Presentation (name: String, modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Résultats de recherche",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun CadreFilms (name: String, modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Films trouvés ",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun RappelRecherche (name: String, modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Votre recherche était :",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(5.dp)
        )
    }
}