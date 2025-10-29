package com.example.contactapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.contactapp.RouterContactApp
import com.example.contactapp.ui.navigateClean
import com.example.contactapp.ui.splash.AppState
import com.example.contactapp.ui.splash.SplashScreenViewModel

fun NavGraphBuilder.splashGraph(
    navController: NavHostController
) {
    composable(
        route = RouterContactApp.SplashScreen.route
    ) {
        val viewModel = hiltViewModel<SplashScreenViewModel>()
        val state by viewModel.uiState.collectAsState()

        when (state.appState) {
            AppState.Loading -> Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            AppState.LoggedOut -> {
                LaunchedEffect(Unit) {
                    navController.navigateClean(RouterContactApp.Login.route)
                }
            }

            AppState.Logged -> {
                LaunchedEffect(Unit) {
                    navController.navigateClean(RouterContactApp.HomeGraph.route)
                }
            }
        }
    }
}

