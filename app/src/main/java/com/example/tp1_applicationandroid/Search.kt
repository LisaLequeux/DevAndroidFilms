package com.example.tp1_applicationandroid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Search (viewModel: MainViewModel, navController: NavController) {
    /*LaunchedEffect(Unit) {
        viewModel.searchMovies()
    }*/
    //var filter by remember { mutableStateOf("All") }

    viewModel.searchMovies()
    viewModel.searchSeries()
    viewModel.searchActeurs()


    val searchMovies by viewModel.movies.collectAsStateWithLifecycle()
    val searchSeries by viewModel.series.collectAsStateWithLifecycle()
    val searchActeurs by viewModel.actors.collectAsStateWithLifecycle()


    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Column {
                Presentation()
                RappelRecherche(viewModel.query)

                CadreFilms()
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier.padding(5.dp).height(350.dp)
                ) {
                    items(searchMovies.size) { index ->
                        val searchMovie = searchMovies[index]
                        Card(
                            modifier = Modifier.padding(5.dp).clickable {
                                navController.navigate("FilmDetails/${searchMovie.id}")
                            },
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

                CadreSeries()
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier.padding(5.dp).height(350.dp)
                ) {
                    items(searchSeries.size) { index ->
                        val searchSerie = searchSeries[index]
                        Card(
                            modifier = Modifier.padding(5.dp).clickable {
                                navController.navigate("SerieDetails/${searchSerie.id}")
                            },
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
                                    model = "https://image.tmdb.org/t/p/w500/${searchSerie.poster_path}",
                                    contentDescription = "poster",
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    searchSerie.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    searchSerie.first_air_date,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                CadreActeurs()
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier.padding(5.dp).height(350.dp)
                ) {
                    /*if(searchActeurs.isEmpty()){
                        NoActeurs()
                    } else {*/
                        items(searchActeurs.size) { index ->
                            val searchActeur = searchActeurs[index]
                            Card(
                                modifier = Modifier.padding(5.dp).clickable {
                                    navController.navigate("ActeurDetails/${searchActeur.id}")
                                },
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
                                        model = "https://image.tmdb.org/t/p/w500/${searchActeur.profile_path}",
                                        contentDescription = "poster",
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        searchActeur.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        searchActeur.known_for_department ?: "",
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    //}
                }
            }
        }
    }
}


@Composable
fun Presentation (modifier: Modifier = Modifier){
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
fun RappelRecherche (query: String, modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Votre recherche était : ${query}",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun CadreFilms (modifier: Modifier = Modifier){
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
fun NoFilms (modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Aucun film trouvé",
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun CadreSeries (modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Séries trouvées ",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun NoSeries (modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Aucune série trouvée",
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun CadreActeurs (modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = "Acteurs trouvés ",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun NoActeurs (){
    Column (
        modifier = Modifier.padding(5.dp)
    ){
        Text(
            text = "Aucun acteur trouvé",
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(5.dp)
        )
    }
}