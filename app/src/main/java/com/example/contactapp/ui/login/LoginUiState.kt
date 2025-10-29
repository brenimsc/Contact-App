package com.example.contactapp.ui.login

data class LoginUiState(
    val user: String = "",
    val password: String = "",
    val showError: Boolean = false,
    val onError: (Boolean) -> Unit = {},
    val onUserChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onClickLogin: () -> Unit = {},
    val logged: Boolean = false
)