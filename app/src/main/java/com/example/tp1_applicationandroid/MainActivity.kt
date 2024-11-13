package com.example.tp1_applicationandroid

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tp1_applicationandroid.ui.theme.TP1ApplicationAndroidTheme
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.composable
import androidx.compose.material3.FloatingActionButton

@Serializable class ProfilDestination
@Serializable class FilmsDestination
@Serializable class SeriesDestination
@Serializable class ActeursDestination
@Serializable class CollectionDestination

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
            var showSearchBar by remember { mutableStateOf(false) }
            //var query by remember { mutableStateOf("") }
            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

            TP1ApplicationAndroidTheme {
                Scaffold(

                    topBar = {
                        if (!isProfilDestination) {
                            if (isLandscape) {
                                Row {
                                    TopLeftFloatingActionButton(navController)
                                    SearchFloatingButton(navController, viewmodel)
                                }

                        } else {
                                Surface(
                                    color = Color.LightGray
                                )
                                {
                                    Column {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(15.dp)
                                        ) {
                                            TextButton(
                                                onClick = { navController.navigate(ProfilDestination()) },
                                                colors = ButtonDefaults.textButtonColors(Color.DarkGray),
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
                                                    .clickable { showSearchBar = true },
                                                tint = Color.DarkGray,
                                            )
                                        }
                                        if (showSearchBar){
                                            TextField(
                                                value = viewmodel.query,
                                                onValueChange = { viewmodel.query = it },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(10.dp),
                                                placeholder = { Text("Entrez votre recherche...") },
                                                singleLine = true,
                                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                                                keyboardActions = KeyboardActions(
                                                    onSearch = {
                                                        showSearchBar = false
                                                        viewmodel.searchMovies()
                                                    }
                                                ),
                                                trailingIcon = {
                                                    TextButton(
                                                        onClick = {
                                                            showSearchBar = false
                                                            viewmodel.searchMovies()
                                                            navController.navigate("search/${viewmodel.query}")
                                                        },
                                                        colors = ButtonDefaults.textButtonColors(Color.White),
                                                        modifier = Modifier.padding(5.dp)
                                                    ) {
                                                        Text(text="Rechercher")
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    },

                    bottomBar = {
                        if(!isProfilDestination) {
                            /*if (isLandscape) {
                                CustomNavigationRail(navController)
                            } else {*/
                                Surface(
                                    color = Color.LightGray
                                )
                                {
                                    NavigationBar(
                                        containerColor = Color.LightGray,
                                        modifier = Modifier.displayCutoutPadding().height(80.dp)//.clip(RoundedCornerShape(20.dp)),
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
                                            onClick = { navController.navigate(ActeursDestination()) }
                                        )
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_photo_library_24),
                                                    contentDescription = "collection",
                                                    Modifier.size(20.dp),
                                                    tint = Color.DarkGray
                                                )
                                            },
                                            label = { Text("Collection") },
                                            selected = currentDestination?.hasRoute<CollectionDestination>() == true,
                                            onClick = { navController.navigate(CollectionDestination()) }
                                        )
                                    }
                                //}
                            }
                        }
                    }
                )
                { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                onClick = { showSearchBar = false },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    ) {
                        /*Row {
                            if (isLandscape && !isProfilDestination) {
                                CustomNavigationRail(navController)
                            }*/
                        NavHost(
                            navController,
                            startDestination = ProfilDestination(),
                            Modifier.padding(innerPadding)
                        ) {
                            composable<ProfilDestination> { ProfilHome(navController) }
                            composable<FilmsDestination> { Films(viewmodel, navController) }
                            composable<SeriesDestination> { Series(viewmodel, navController) }
                            composable<ActeursDestination> { Acteurs(viewmodel, navController) }
                            composable<CollectionDestination>{ Collection(viewmodel) }
                            composable("FilmDetails/{filmId}") { backStackEntry ->
                                val filmId = backStackEntry.arguments?.getString("filmId")
                                    ?: return@composable
                                FilmDetails(viewmodel, filmId)
                            }
                            composable("SerieDetails/{serieId}") { backStackEntry ->
                                val serieId = backStackEntry.arguments?.getString("serieId")
                                    ?: return@composable
                                SerieDetails(viewmodel, serieId)
                            }
                            composable("ActeurDetails/{actorId}") { backStackEntry ->
                                val actorId = backStackEntry.arguments?.getString("actorId")
                                    ?: return@composable
                                ActeurDetails(viewmodel, actorId)
                            }
                            composable("Search/{query}") { backStackEntry ->
                                val query = backStackEntry.arguments?.getString("query")
                                    ?: return@composable
                                Search(viewmodel, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomNavigationRail(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isProfilDestination = currentDestination?.hasRoute<ProfilDestination>() == true

    if (!isProfilDestination) {
        androidx.compose.material3.NavigationRail(
            modifier = Modifier.fillMaxHeight().width(72.dp),
            containerColor = Color.LightGray
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_movie_24),
                        contentDescription = "films",
                        modifier = Modifier.size(20.dp).align(Alignment.CenterHorizontally),
                        tint = Color.DarkGray
                    )
                },
                label = { Text("Films") },
                selected = currentDestination?.hasRoute<FilmsDestination>() == true,
                onClick = { navController.navigate(FilmsDestination()) }
            )
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_tv_24),
                        contentDescription = "series",
                        modifier = Modifier.size(20.dp).align(Alignment.CenterHorizontally),
                        tint = Color.DarkGray
                    )
                },
                label = { Text("Séries") },
                selected = currentDestination?.hasRoute<SeriesDestination>() == true,
                onClick = { navController.navigate(SeriesDestination()) }
            )
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = "acteurs",
                        modifier = Modifier.size(20.dp).align(Alignment.CenterHorizontally),
                        tint = Color.DarkGray
                    )
                },
                label = { Text("Acteurs") },
                selected = currentDestination?.hasRoute<ActeursDestination>() == true,
                onClick = { navController.navigate(ActeursDestination()) }
            )
        }
    }
}

@Composable
fun TopLeftFloatingActionButton(navController: NavController){
    FloatingActionButton(
        onClick = { navController.navigate(ProfilDestination()) },
        containerColor = Color.DarkGray,
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_person_24),
            contentDescription = "profil",
            tint = Color.White
        )
    }
}

@Composable
fun SearchFloatingButton(navController: NavController, viewmodel: MainViewModel){
    var showSearchBar by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { showSearchBar = !showSearchBar },
        containerColor = Color.LightGray,
        modifier = Modifier.padding(5.dp)
    ) {
        if (showSearchBar) {
            TextField(
                value = viewmodel.query,
                onValueChange = { viewmodel.query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text("Entrez votre recherche...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        showSearchBar = false
                        viewmodel.searchMovies()
                    }
                ),
                trailingIcon = {
                    TextButton(
                        onClick = {
                            showSearchBar = false
                            viewmodel.searchMovies()
                            navController.navigate("search/${viewmodel.query}")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            Color.White
                        ),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(text = "Rechercher")
                    }
                }
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = "recherche",
                tint = Color.DarkGray
            )
        }
    }

}

@Composable
fun CustomTopBar(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isProfilDestination = currentDestination?.hasRoute<ProfilDestination>() == true
    var showSearchBar by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    if(!isProfilDestination) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .width(72.dp)
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
                    .clickable { showSearchBar = true },
                tint = Color.DarkGray
            )
        }
        if (showSearchBar) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text("Entrez votre recherche...") },
                singleLine = true,
                trailingIcon = {
                    TextButton(
                        onClick = {
                            showSearchBar = false
                            navController.navigate("search/$query")
                        },
                        colors = ButtonDefaults.textButtonColors(Color.White)
                    ) {
                        Text(text = "Rechercher")
                    }
                }
            )
        }
    }
}
