package com.example.contactapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.contactapp.DetailsContact
import com.example.contactapp.R
import com.example.contactapp.extensions.ID_CONTACT
import com.example.contactapp.extensions.showMessage
import com.example.contactapp.ui.details.DetailsContactScreen
import com.example.contactapp.ui.details.DetailsContactViewModel
import com.example.contactapp.ui.navigateToFormsContact

fun NavGraphBuilder.detailsContactGraph(
    navController: NavHostController
) {
    composable(
        route = DetailsContact.routeWithArguments,
        arguments = DetailsContact.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTACT
        )?.let { idContact ->

            val viewModel = hiltViewModel<DetailsContactViewModel>()
            val state by viewModel.uiState.collectAsState()

            val context = LocalContext.current

            DetailsContactScreen(
                state = state,
                onClickBack = { navController.popBackStack() },
                onClickDelete = {
                    viewModel.deleteContact()
                    context.showMessage(context.getString(R.string.contato_apagado))
                    navController.popBackStack()
                },
                onClickEdit = { navController.navigateToFormsContact(idContact) })
        }
    }
}