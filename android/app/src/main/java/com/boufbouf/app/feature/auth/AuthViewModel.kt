package com.boufbouf.app.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boufbouf.app.core.network.UserResponse
import com.boufbouf.app.feature.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val emailOrPhone: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val currentUser: UserResponse? = null,
    val errorMessage: String? = null,
)

class AuthViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateEmailOrPhone(emailOrPhone: String) {
        _uiState.value = _uiState.value.copy(
            emailOrPhone = emailOrPhone,
            errorMessage = null,
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null,
        )
    }

    fun login() = viewModelScope.launch {
        val emailOrPhone = _uiState.value.emailOrPhone.trim()
        val password = _uiState.value.password

        if (emailOrPhone.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Entre ton email ou ton numéro de téléphone.",
            )
            return@launch
        }

        if (password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Entre ton mot de passe.",
            )
            return@launch
        }

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null,
        )

        try {
            repository.login(
                emailOrPhone = emailOrPhone,
                password = password,
            )

            val user = repository.getCurrentUser()

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoggedIn = true,
                currentUser = user,
                errorMessage = null,
            )
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoggedIn = false,
                errorMessage = "Impossible de se connecter. Vérifie tes identifiants.",
            )
        }
    }
}
