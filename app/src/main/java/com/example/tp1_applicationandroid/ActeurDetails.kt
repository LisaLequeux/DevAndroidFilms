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
fun ActeurDetails(viewModel: MainViewModel, actorId: String) {
    LaunchedEffect(Unit) {
        viewModel.getActorDetails(actorId)
    }

    val actors by viewModel.actors.collectAsState()
    val actor = actors.find { it.id == actorId.toInt() }

    actor?.let {
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
                            model = "https://image.tmdb.org/t/p/w500/${actor.profile_path}",
                            contentDescription = "Actor Profile",
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
                                text = actor.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Connu pour : ${actor.known_for_department}",
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(5.dp)
                            )
                            /*Text(
                                text = "Popularity: ${actor.popularity}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(5.dp)
                            )*/
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Column(
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
                        Text(
                            text = "Biographie:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = actor.biography ?: "Biographie indisponible",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Column (
                        horizontalAlignment = AbsoluteAlignment.Left,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ) {
                        Text(
                            text = "Informations suplémentaires :",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            //Pour afficher le poster en entier :
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${actor.profile_path}",
                                contentDescription = "Movie Poster",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    //.height(200.dp)
                                    .padding(bottom = 5.dp)
                            )
                            Column {
                                if (actor.original_name != null) {
                                    Text(
                                        text = "Nom originel : ${actor.original_name}",
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Text(
                                    text = "Id : ${actor.id}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Text(
                                    text = "Né(é) le : ${actor.birthday}",
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(5.dp)
                                )
                                if (actor.deathday != null) {
                                    Text(
                                        text = "Décédé(e) le : ${actor.deathday}",
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                } else {
                                    Text(
                                        text = "Vivant(e)",
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    } ?: run {
        Text(
            text = "Actor not found",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
    }
}