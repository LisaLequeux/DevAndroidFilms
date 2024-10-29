package com.example.tp1_applicationandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tp1_applicationandroid.ui.theme.TP1ApplicationAndroidTheme
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.composable

@Serializable class ProfilDestination
@Serializable class FilmsDestination
@Serializable class SeriesDestination
@Serializable class ActeursDestination
@Serializable class SearchDestination

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel : MainViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val isProfilDestination =currentDestination?.hasRoute<ProfilDestination>() == true
            TP1ApplicationAndroidTheme {
                Scaffold(
                    topBar = {
                        if (!isProfilDestination) {
                            Surface(
                                color = Color.LightGray
                            )
                            {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                ) {
                                    TextButton(
                                        onClick = { navController.navigate(ProfilDestination()) },
                                        colors = ButtonDefaults.textButtonColors(Color.DarkGray)
                                    ) {
                                        Text(
                                            "Profil",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = Color.LightGray
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_search_24),
                                        contentDescription = "recherche",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clickable { navController.navigate(SearchDestination()) },
                                        tint = Color.DarkGray
                                    )
                                }
                            }
                        }
                    },

                    bottomBar = {
                        if(!isProfilDestination) {
                            Surface(
                                color = Color.LightGray
                            )
                            {
                                NavigationBar(
                                    containerColor = Color.LightGray,
                                    modifier = Modifier.displayCutoutPadding()//.clip(RoundedCornerShape(20.dp)),
                                ) {
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_movie_24),
                                                contentDescription = "films",
                                                Modifier.size(20.dp),
                                                tint = Color.DarkGray
                                            )
                                        },
                                        label = { Text("Films") },
                                        selected = currentDestination?.hasRoute<FilmsDestination>() == true,
                                        onClick = { navController.navigate(FilmsDestination()) })
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_tv_24),
                                                contentDescription = "series",
                                                Modifier.size(20.dp),
                                                tint = Color.DarkGray
                                            )
                                        },
                                        label = { Text("Séries") },
                                        selected = currentDestination?.hasRoute<SeriesDestination>() == true,
                                        onClick = { navController.navigate(SeriesDestination()) })
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_person_24),
                                                contentDescription = "acteurs",
                                                Modifier.size(20.dp),
                                                tint = Color.DarkGray
                                            )
                                        },
                                        label = { Text("Acteurs") },
                                        selected = currentDestination?.hasRoute<ActeursDestination>() == true,
                                        onClick = { navController.navigate(ActeursDestination()) })
                                }
                            }
                        }
                    }
                )
                { innerPadding ->
                    NavHost(navController, startDestination = ProfilDestination(),
                        Modifier.padding(innerPadding)) {
                        composable<ProfilDestination> { ProfilHome(navController) }
                        composable<FilmsDestination> { Films(viewmodel) }
                        composable<SeriesDestination> { Series(viewmodel) }
                        composable<ActeursDestination> { Acteurs(viewmodel) }
                        composable<SearchDestination> { Search(viewmodel) }
                    }
                }
            }
        }
    }
}
