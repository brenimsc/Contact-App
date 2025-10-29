package com.example.contactapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.contactapp.RouterContactApp
import com.example.contactapp.ui.home.ListContactScreen
import com.example.contactapp.ui.home.ListContactViewModel
import com.example.contactapp.ui.navigateToDetails
import com.example.contactapp.ui.navigateToFormsContact
import com.example.contactapp.ui.navigateToLoginLoggedOut

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = RouterContactApp.ListContact.route,
        route = RouterContactApp.HomeGraph.route,
    ) {

        composable(route = RouterContactApp.ListContact.route) {
            val viewModel = hiltViewModel<ListContactViewModel>()
            val state by viewModel.uiState.collectAsState()

            ListContactScreen(
                state = state,
                onClickOpenDetails = { idContact ->
                    navController.navigateToDetails(idContact)
                },
                onClickOpenRegister = {
                    navController.navigateToFormsContact()
                },
                onClickExit = {
                    viewModel.logOut()
                    navController.navigateToLoginLoggedOut()
                })
        }
    }
}

