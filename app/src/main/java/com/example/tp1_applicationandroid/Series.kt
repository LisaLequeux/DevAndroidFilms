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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Series (viewModel: MainViewModel, navController: NavController) {
    //Cette fonction permet de récupérer et de tout actualiser au démarrage de l'application
    LaunchedEffect(Unit) {
        viewModel.getSeries()
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isPortrait = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    //On vérifie si on est en mode portrait ou paysage
    if (isPortrait) {
        seriePortait(navController, viewModel)
    } else {
        seriePaysage(navController, viewModel)
    }
}

@Composable
fun seriePortait(navController: NavController, viewModel: MainViewModel){
    val series by viewModel.series.collectAsStateWithLifecycle()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(5.dp)
    ) {
        items(series.size) { index ->
            val serie = series[index]
            Card(
                modifier = Modifier.padding(5.dp).clickable {
                    navController.navigate("serieDetails/${serie.id}")
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
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                        contentDescription = "poster",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        serie.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        serie.first_air_date,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun seriePaysage(navController: NavController, viewModel: MainViewModel){
    val series by viewModel.series.collectAsStateWithLifecycle()
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(5.dp)
    ) {
        items(series.size) { index ->
            val serie = series[index]
            Card(
                modifier = Modifier.padding(10.dp).clickable {
                    navController.navigate("serieDetails/${serie.id}")
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
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                        contentDescription = "poster",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        serie.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        serie.first_air_date,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}