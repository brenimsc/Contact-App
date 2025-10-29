package com.example.contactapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.contactapp.RouterContactApp
import com.example.contactapp.ui.login.FormLoginScreen
import com.example.contactapp.ui.login.FormLoginViewModel
import com.example.contactapp.ui.login.LoginScreen
import com.example.contactapp.ui.login.LoginViewModel
import com.example.contactapp.ui.navigateClean

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = RouterContactApp.Login.route,
        route = RouterContactApp.LoginGraph.route
    ) {
        composable(
            route = RouterContactApp.Login.route,
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            if (state.logged) {
                LaunchedEffect(Unit) {
                    navController.navigateClean(RouterContactApp.HomeGraph.route)
                }
            }
            LoginScreen(
                state = state,
                onClickLogin = {
                    viewModel.tryLogin()
                },
                onClickCreateLogin = {
                    navController.navigate(RouterContactApp.FormsLogin.route)
                }
            )
        }

        composable(
            route = RouterContactApp.FormsLogin.route,
        ) {
            val viewModel = hiltViewModel<FormLoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            FormLoginScreen(
                state = state,
                onClickSave = {
                    viewModel.setUserPreferences()
                    navController.navigateClean(RouterContactApp.Login.route)
                }
            )
        }
    }
}