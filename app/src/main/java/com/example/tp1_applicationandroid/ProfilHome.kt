package com.example.tp1_applicationandroid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ProfilHome(navController: NavHostController) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.Center

                            ) {
                                Photo(
                                    name = "Android",
                                )
                                Identite(
                                    name = "Android",
                                )
                                Contact(
                                    name = "Android",
                                )
                                BtnDemarrer(navController = navController)
                            }
                        }

                        else -> {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .fillMaxSize()
                                    .padding(5.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Photo(
                                        name = "Android"
                                    )
                                    Identite(
                                        name = "Android"
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Contact(
                                        name = "Android"
                                    )
                                    BtnDemarrer(navController = navController)
                                }
                            }
                        }
                    }
                }


        @Composable
        fun Identite(name: String, modifier: Modifier = Modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(20.dp)
            ) {
                Text(text = "Lisa LEQUEUX", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Etudiante en Informatique pour la Santé")
                Text(text = "Ecole d'ingénieurs ISIS-INSA-INU Champollion")
            }
        }

        @Composable
        fun Contact(name: String, modifier: Modifier = Modifier) {
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(20.dp)
                ) {
                    Row() {
                        Column {
                            Icon(
                                painter = painterResource(R.drawable.baseline_alternate_email_24),
                                contentDescription = "email",
                                Modifier.size(20.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.baseline_insert_link_24),
                                contentDescription = "Linkedin"
                            )
                        }
                        Column {
                            Text(text = "lisa.lequeux@etud.univ-jfc.fr")
                            Text(text = "linkedin.com/in/lisa-lequeux-01958b256")
                        }
                    }
                }
            }
        }

        @Composable
        fun Photo(name: String, modifier: Modifier = Modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.laco),
                    contentDescription = "LacO",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)
                        .border(BorderStroke(5.dp, Color.DarkGray), CircleShape)
                )
            }
        }

        @Composable
        fun BtnDemarrer(navController: NavController) {
            Button(
                onClick = { navController.navigate(FilmsDestination()) }
            ) {
                Text(text = "Démarrer")
            }
        }