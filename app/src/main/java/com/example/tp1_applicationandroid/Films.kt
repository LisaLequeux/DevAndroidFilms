package com.example.tp1_applicationandroid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Films (viewModel: MainViewModel, navController: NavHostController) {
    //Cette fonction permet de récupérer et de tout actualiser au démarrage de l'application
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }
    //La fonction collectAsStateWithLifecycle permet de sortir de l'état provisoir
    val movies by viewModel.movies.collectAsStateWithLifecycle()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            LazyVerticalGrid(
                /*columns = when (windowSizeClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> GridCells.Fixed(2)
                    WindowWidthSizeClass.MEDIUM -> GridCells.Fixed(3)
                    WindowWidthSizeClass.EXPANDED -> GridCells.Fixed(4)
                    else -> GridCells.Fixed(2)
                },*/
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp)
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate("FilmDetails/${movie.id}")
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
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "poster",
                                alignment = Alignment.Center,
                            )
                            Text(
                                movie.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                movie.release_date,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }



        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(10.dp)
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    Card(
                        modifier = Modifier.padding(10.dp).clickable {
                            navController.navigate("FilmDetails/${movie.id}")
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
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "poster"
                            )
                            Text(
                                movie.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                movie.release_date,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

        }
    }
    //Ancienne fonction qui mettait tous les films en 1 colonne
    /*LazyColumn{
        movies.forEach {
            item {
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${it.poster_path}",
                        contentDescription = "poster"
                    )
                    Text(it.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }*/
}
