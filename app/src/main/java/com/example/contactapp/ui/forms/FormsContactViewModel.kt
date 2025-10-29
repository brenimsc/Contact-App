package com.example.contactapp.ui.forms

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.R
import com.example.contactapp.data.Contact
import com.example.contactapp.database.ContactDao
import com.example.contactapp.extensions.ID_CONTACT
import com.example.contactapp.extensions.formatDateToString
import com.example.contactapp.extensions.formatStringToDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FormsContactViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dao: ContactDao
) : ViewModel() {

    private val idContact = savedStateHandle.get<Long>(ID_CONTACT)
    private var contact: Contact? = null

    private val _uiState = MutableStateFlow(FormsContactUiState())
    val uiState: StateFlow<FormsContactUiState>
        get() = _uiState.asStateFlow()


    init {
        loadContact()

        _uiState.update { state ->
            state.copy(onNameChange = {
                _uiState.value = _uiState.value.copy(
                    name = it
                )
            }, onLastnameChange = {
                _uiState.value = _uiState.value.copy(
                    lastname = it
                )
            }, onPhoneChange = {
                _uiState.value = _uiState.value.copy(
                    phone = it
                )
            }, onPhotoPerfilChange = {
                _uiState.value = _uiState.value.copy(
                    photoPerfil = it
                )
            }, onBirthdayChange = {
                _uiState.value = _uiState.value.copy(
                    birthday = it.formatStringToDate(), showDialogDate = false
                )
            }, onShowDialogImageChange = {
                _uiState.value = _uiState.value.copy(
                    showDialogImage = it
                )
            }, onShowDialogDateChange = {
                _uiState.value = _uiState.value.copy(
                    showDialogDate = it
                )
            })
        }
    }

    private fun loadContact() {
        idContact?.let {
            viewModelScope.launch {
                dao.get(idContact).collect {
                    it?.let {
                        contact = it
                        contact?.let { contact ->
                            _uiState.update {
                                with(contact) {
                                    it.copy(
                                        id = id,
                                        name = name,
                                        lastname = lastName,
                                        phone = phone,
                                        photoPerfil = photo,
                                        birthday = birthday,
                                        titleAppBar = R.string.titulo_editar_contato
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            with(_uiState.value) {
                dao.insert(
                    Contact(
                        id = id,
                        name = name,
                        lastName = lastname,
                        phone = phone,
                        photo = photoPerfil,
                        birthday = birthday
                    )
                )
            }
        }
    }

    fun setTextBirthday(textBirthday: String) {
        _uiState.update {
            it.copy(textBirthday = _uiState.value.birthday?.formatDateToString() ?: textBirthday)
        }
    }

    fun loadImage(url: String) {
        _uiState.value = _uiState.value.copy(
            photoPerfil = url, showDialogImage = false
        )
    }
}
