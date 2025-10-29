package com.example.contactapp.ui.login

data class FormLoginUiState(
    val name: String = "",
    val user: String = "",
    val password: String = "",
    val onNameChange: (String) -> Unit = {},
    val onUserChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onClickSave: () -> Unit = {}
)