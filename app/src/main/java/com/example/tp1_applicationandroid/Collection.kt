package com.example.tp1_applicationandroid

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun Collection (viewModel: MainViewModel) {
    //Cette fonction permet de récupérer et de tout actualiser au démarrage de l'application
    LaunchedEffect(Unit) {
        viewModel.collectionHorror()
    }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isPortrait = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
}