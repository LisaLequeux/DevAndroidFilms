package com.example.tp1_applicationandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SerieDetails(viewModel: MainViewModel, serieId: String) {
    LaunchedEffect(Unit) {
        viewModel.getSerieDetails(serieId)
    }

    val series by viewModel.series.collectAsState()
    val serie = series.find { it.id == serieId.toInt() }

    serie?.let {
        val genreNames = serie.genres.joinToString(", ") { it.name }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.padding(5.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                            contentDescription = "Serie Poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .padding(bottom = 5.dp)
                        )

                        Column(
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier.fillMaxSize().padding(5.dp)
                        ) {
                            Text(
                                text = serie.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                            if (serie.name != serie.original_name) {
                                Text(
                                    text = "Titre original : ${serie.original_name}",
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Text(
                                    text = "Langue originale : ${serie.original_language}",
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                            Text(
                                text = "Id : ${serie.id}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Première diffusion : ${serie.first_air_date}",
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
                        Text(
                            text = "Résumé :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = serie.overview,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
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
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
                        Text(
                            text = "Avis du public :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Noté : ${serie.vote_average}/10",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Popularité : ${serie.popularity}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Text(
                            text = "Nombre de votes : ${serie.vote_count}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    } ?: run {
        Text(
            text = "Série introuvable",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}