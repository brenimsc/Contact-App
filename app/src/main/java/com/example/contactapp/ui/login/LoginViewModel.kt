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
class LoginViewModel @Inject constructor(
    private val loginPreferences: LoginPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
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
                onError = {
                    _uiState.value = _uiState.value.copy(
                        showError = it
                    )
                },
            )
        }
    }

    fun tryLogin() {
        viewModelScope.launch {
            loginPreferences.apply {
                getUserAndPassword { user, password ->

                    if (user == uiState.value.user && password == uiState.value.password) {
                        loginUser()

                        viewModelScope.launch {
                            setLoggedPreferences(uiState.value.logged)
                        }
                    } else {
                        uiState.value.onError(true)
                    }
                }
            }
        }
    }

    private fun loginUser() {
        _uiState.value = _uiState.value.copy(
            logged = true
        )
    }
}


