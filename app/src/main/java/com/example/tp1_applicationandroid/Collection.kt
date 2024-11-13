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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage


@Composable
fun Collection (viewModel: MainViewModel) {
    //Cette fonction permet de récupérer et de tout actualiser au démarrage de l'application
    LaunchedEffect(Unit) {
        viewModel.collectionHorror()
    }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isPortrait = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    if(isPortrait){
        collectionPortrait(viewModel)
        //Text(text = "Essai")
    }else {
        collectionPaysage(viewModel)
    }
}

@Composable
fun collectionPortrait(viewModel: MainViewModel){
    val horror = viewModel.horror.collectAsStateWithLifecycle().value
    //val query = remember(horror) { horror.toList() }
    //if (query.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(5.dp)
        ) {
            items(horror.size) { index ->
                val horreur = horror[index]
                Card(
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
                            model = "https://image.tmdb.org/t/p/w500/${horreur.backdrop_path}",
                            contentDescription = "poster",
                            alignment = Alignment.Center,
                        )
                        Text(
                            horreur.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        //}
    }
}

@Composable
fun collectionPaysage(viewModel: MainViewModel){
    val horror by viewModel.horror.collectAsStateWithLifecycle()
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(5.dp)
    ) {
        items(horror.size) { index ->
            val horreur = horror[index]
            Card(
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
                        model = "https://image.tmdb.org/t/p/w500/${horreur.backdrop_path}",
                        contentDescription = "poster",
                        alignment = Alignment.Center,
                    )
                    Text(
                        horreur.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}