package com.example.contactapp

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.contactapp.extensions.ID_CONTACT

sealed class RouterContactApp(val route: String) {
    object LoginGraph : RouterContactApp("grafico_login")
    object HomeGraph : RouterContactApp("grafico_home")
    object SplashScreen : RouterContactApp("splashScreen")
    object ListContact : RouterContactApp("lista_contatos")
    object FormsLogin : RouterContactApp("formulario_login")
    object Login : RouterContactApp("login")
}

object FormsContact {
    const val route = "formulario_contato"
    const val routeWithArguments = "$route/{$ID_CONTACT}"
    val arguments = listOf(
        navArgument(ID_CONTACT) {
            defaultValue = 0L
            type = NavType.LongType
        }
    )
}

object DetailsContact {
    const val route = "detalhes_contato"
    const val routeWithArguments = "$route/{$ID_CONTACT}"
    val arguments = listOf(
        navArgument(ID_CONTACT) {
            defaultValue = 0L
            type = NavType.LongType
        }
    )
}