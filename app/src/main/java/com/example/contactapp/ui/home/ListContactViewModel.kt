package com.example.contactapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.database.ContactDao
import com.example.contactapp.preferences.LoginPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListContactViewModel @Inject constructor(
    private val dao: ContactDao,
    private val loginPreferences: LoginPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListContactUiState())
    val uiState: StateFlow<ListContactUiState>
        get() = _uiState.asStateFlow()

    init {
        getAllContacts()

        _uiState.update {
            it.copy(
                onSearchChange = {
                    _uiState.value = uiState.value.copy(
                        searchText = it
                    )

                    getContactsName(it)
                }
            )
        }
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            val contacts = dao.getAll()
            contacts.collect { contacts ->
                _uiState.update {
                    it.copy(contacts = contacts)
                }
            }
        }
    }

    fun getContactsName(name: String) {
        viewModelScope.launch {
            takeUnless { name.isEmpty() }?.run {
                val contacts = dao.getName(name)

                contacts.collect { contacts ->
                    _uiState.update {
                        it.copy(contacts = contacts)
                    }
                }
            } ?: getAllContacts()
        }
    }

    fun logOut() {
        viewModelScope.launch {
            loginPreferences.setLoggedPreferences(false)
        }
    }
}