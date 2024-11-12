package com.example.tp1_applicationandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun FilmDetails(viewModel: MainViewModel,  filmId: String/*, navController: NavController*/) {
    //Cette fonction permet de récupérer et de tout actualiser au démarrage de l'application
    LaunchedEffect(Unit) {
        viewModel.getFilmDetails(filmId)
    }

    //La fonction collectAsStateWithLifecycle permet de sortir de l'état provisoir
    val movies by viewModel.movies.collectAsState()
    val movie = movies.find { it.id == filmId.toInt() }

    //vérifie que l'objet movie n'est jamais null, si c'est le cas le let ne s'applique pas mais le run
    movie?.let {
        val genreNames = movie.genres.joinToString(", ") { it.name }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.padding(5.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                ) {

                    Card (
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                            contentDescription = "Movie Poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                //.fillMaxHeight()
                                .height(200.dp)
                                .padding(bottom = 5.dp)
                        )

                        Column (
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier.fillMaxSize().padding(5.dp)
                        ){
                            Text(
                                text = movie.title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Sorti le : ${movie.release_date}",
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Column(
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ){
                        Text (
                            text = "Résumé :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = movie.overview,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Column(
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ){
                        Text(
                            text = "Genres :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = genreNames,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Column(
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
                        Text(
                            text = "Avis du public :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Noté : ${movie.vote_average}/10",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Popularité : ${movie.popularity}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Text(
                            text = "Nombre de votes : ${movie.vote_count}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Column (
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ){
                        Text(
                            text = "Informations suplémentaires :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            //Pour afficher le poster en entier :
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "Movie Poster",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    //.height(200.dp)
                                    .padding(bottom = 5.dp)
                            )
                            Column {
                                if (movie.title != movie.original_title) {
                                    Text(
                                        text = "Titre original : ${movie.original_title}",
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "Langue originale : ${movie.original_language}",
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Text(
                                    text = "Id : ${movie.id}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }

                        }
                    }
                    }
                }
            }
    }?: run {
        Text(
            text = "Film introuvable",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}