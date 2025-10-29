package com.example.contactapp.ui.forms

import androidx.annotation.StringRes
import com.example.contactapp.R
import java.util.Date

data class FormsContactUiState(
    val id: Long = 0L,
    val name: String = "",
    val lastname: String = "",
    val phone: String = "",
    val photoPerfil: String = "",
    val birthday: Date? = null,
    val showDialogImage: Boolean = false,
    val showDialogDate: Boolean = false,
    val onNameChange: (String) -> Unit = {},
    val onLastnameChange: (String) -> Unit = {},
    val onPhoneChange: (String) -> Unit = {},
    val onPhotoPerfilChange: (String) -> Unit = {},
    val onBirthdayChange: (String) -> Unit = {},
    val onShowDialogImageChange: (Boolean) -> Unit = {},
    val onShowDialogDateChange: (Boolean) -> Unit = {},
    val textBirthday: String = "",
    @StringRes val titleAppBar: Int? = R.string.titulo_cadastro_contato,
)