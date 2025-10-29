package com.example.contactapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.preferences.LoginPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormLoginViewModel @Inject constructor(
    private val loginPreferences: LoginPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormLoginUiState())
    val uiState: StateFlow<FormLoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onUserChange = {
                    _uiState.value = _uiState.value.copy(
                        user = it
                    )
                },
                onPasswordChange = {
                    _uiState.value = _uiState.value.copy(
                        password = it
                    )
                },
            )
        }
    }

    fun setUserPreferences() {
        viewModelScope.launch {
            loginPreferences.apply {
                setUserPreferences(uiState.value.user)
                setPasswordPreferences(uiState.value.password)
            }
        }
    }
}
