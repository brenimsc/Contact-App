package com.example.contactapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.database.ContactDao
import com.example.contactapp.extensions.ID_CONTACT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsContactViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: ContactDao
) : ViewModel() {

    private val idContact = savedStateHandle.get<Long>(ID_CONTACT)

    private val _uiState = MutableStateFlow(DetailsContactUiState())
    val uiState: StateFlow<DetailsContactUiState>
        get() = _uiState.asStateFlow()

    init {
        loadContact()
    }

    private fun loadContact() {
        viewModelScope.launch {
            idContact?.let {
                dao.get(it).collect {
                    it?.let { contact ->
                        with(contact) {
                            _uiState.update {
                                it.copy(
                                    id = id,
                                    name = name,
                                    lastname = lastName,
                                    phone = phone,
                                    photoPerfil = photo,
                                    birthday = birthday
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun deleteContact() {
        viewModelScope.launch {
            idContact?.let {
                dao.delete(it)
            }
        }
    }
}