package com.example.contactapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.contactapp.DetailsContact
import com.example.contactapp.FormsContact
import com.example.contactapp.RouterContactApp
import com.example.contactapp.navigation.detailsContactGraph
import com.example.contactapp.navigation.formsContactGraph
import com.example.contactapp.navigation.homeGraph
import com.example.contactapp.navigation.loginGraph
import com.example.contactapp.navigation.splashGraph

@Composable
fun ContactAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RouterContactApp.SplashScreen.route,
        modifier = modifier
    ) {
        splashGraph(navController)
        loginGraph(navController)
        homeGraph(navController)
        formsContactGraph(navController)
        detailsContactGraph(navController)
    }
}


fun NavHostController.navigateDirect(rota: String) = this.navigate(rota) {
    popUpTo(this@navigateDirect.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateClean(route: String) = this.navigate(route) {
    popUpTo(0)
}

fun NavHostController.navigateToDetails(idContact: Long) {
    navigateDirect("${DetailsContact.route}/$idContact")
}

fun NavHostController.navigateToFormsContact(idContact: Long = 0L) {
    navigate("${FormsContact.route}/$idContact")
}

fun NavHostController.navigateToLoginLoggedOut() {
    popBackStack(RouterContactApp.ListContact.route, true)
    navigateDirect(RouterContactApp.LoginGraph.route)
}