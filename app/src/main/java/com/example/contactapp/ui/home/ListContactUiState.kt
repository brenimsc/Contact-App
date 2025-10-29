package com.example.contactapp.ui.home

import com.example.contactapp.data.Contact

data class ListContactUiState(
    val contacts: List<Contact> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {},
    val logged: Boolean = true
)