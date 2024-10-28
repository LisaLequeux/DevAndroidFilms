package com.example.tp1_applicationandroid

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Acteurs(viewModel: MainViewModel){
    //Initialisation au démarrage
    LaunchedEffect(Unit) {
        viewModel.getActors()
    }

    //Récupération des acteurs
    val acteurs by viewModel.actors.collectAsStateWithLifecycle()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp)
            ) {
                items(acteurs.size) { index ->
                    val acteur = acteurs[index]
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
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                            contentDescription = "poster",
                        )
                        Text(acteur.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            acteur.known_for_department,
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp
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
                items(acteurs.size) { index ->
                    val acteur = acteurs[index]
                    Card(
                        modifier = Modifier.padding(10.dp),
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
                                model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                contentDescription = "poster",
                            )
                            Text(acteur.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(
                                acteur.known_for_department,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}