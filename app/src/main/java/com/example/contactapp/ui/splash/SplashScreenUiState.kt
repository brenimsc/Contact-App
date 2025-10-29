package com.example.contactapp.ui.splash

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading
)

sealed class AppState {
    object Loading : AppState()
    object Logged : AppState()
    object LoggedOut : AppState()
}