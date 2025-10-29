package com.example.contactapp.ui.details

import java.util.Date

data class DetailsContactUiState(
    val id: Long = 0L,
    val name: String = "",
    val lastname: String = "",
    val phone: String = "",
    val photoPerfil: String = "",
    val birthday: Date? = null,
)