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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Acteurs(viewModel: MainViewModel, navController: NavHostController) {
    //Initialisation au démarrage
    LaunchedEffect(Unit) {
        viewModel.getActors()
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isPortrait = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    //On vérifie si on est en mode portrait ou paysage
    if (isPortrait) {
        acteurPortait(navController, viewModel)
    } else {
        acteurPaysage(navController, viewModel)
    }
}

@Composable
fun acteurPortait(navController: NavController, viewModel: MainViewModel) {
    val acteurs by viewModel.actors.collectAsStateWithLifecycle()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(5.dp)
    ) {
        items(acteurs.size) { index ->
            val acteur = acteurs[index]
            Card(
                modifier = Modifier.padding(5.dp).clickable {
                    navController.navigate("ActeurDetails/${acteur.id}")
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
                        model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                        contentDescription = "poster",
                    )
                    Text(
                        acteur.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    /*if(acteur.name != acteur.original_name) {
                    Text(
                        acteur.original_name ?: "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
                }*/
                    Text(
                        acteur.known_for_department ?: "",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun acteurPaysage(navController: NavController, viewModel: MainViewModel){
    val acteurs by viewModel.actors.collectAsStateWithLifecycle()
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(5.dp)
    ) {
        items(acteurs.size) { index ->
            val acteur = acteurs[index]
            Card(
                modifier = Modifier.padding(10.dp).clickable {
                    navController.navigate("ActeurDetails/${acteur.id}")
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
                        model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                        contentDescription = "poster",
                        //placeholder = /*mettre une image par défaut*/
                    )
                    Text(
                        acteur.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    /*if(acteur.name != acteur.original_name) {
                        Text(
                            acteur.original_name ?: "",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp
                        )
                    }*/
                    Text(
                        acteur.known_for_department ?: "",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}