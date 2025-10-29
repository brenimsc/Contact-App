package com.example.contactapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.contactapp.FormsContact
import com.example.contactapp.R
import com.example.contactapp.extensions.ID_CONTACT
import com.example.contactapp.ui.forms.FormsContactScreen
import com.example.contactapp.ui.forms.FormsContactViewModel

fun NavGraphBuilder.formsContactGraph(
    navController: NavHostController
) {
    composable(
        route = FormsContact.routeWithArguments,
        arguments = FormsContact.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTACT
        )?.let { idContact ->

            val viewModel = hiltViewModel<FormsContactViewModel>()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.birthday) {
                viewModel.setTextBirthday(
                    context.getString(R.string.aniversario)
                )
            }

            FormsContactScreen(
                state = state,
                onClickSave = {
                    viewModel.save()
                    navController.popBackStack()
                },
                onLoadImage = {
                    viewModel.loadImage(it)
                }
            )
        }
    }
}