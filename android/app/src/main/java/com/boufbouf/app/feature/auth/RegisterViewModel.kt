package com.boufbouf.app.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boufbouf.app.feature.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val displayName: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val country: String = "Morocco",
    val language: String = "fr",
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val errorMessage: String? = null,
)

class RegisterViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(
            username = value,
            errorMessage = null,
        )
    }

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(
            email = value,
            errorMessage = null,
        )
    }

    fun updatePhoneNumber(value: String) {
        _uiState.value = _uiState.value.copy(
            phoneNumber = value,
            errorMessage = null,
        )
    }

    fun updateDisplayName(value: String) {
        _uiState.value = _uiState.value.copy(
            displayName = value,
            errorMessage = null,
        )
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            errorMessage = null,
        )
    }

    fun updateConfirmPassword(value: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = value,
            errorMessage = null,
        )
    }

    fun updateCountry(value: String) {
        _uiState.value = _uiState.value.copy(
            country = value,
            errorMessage = null,
        )
    }

    fun register() = viewModelScope.launch {
        val state = _uiState.value

        val username = state.username.trim()
        val email = state.email.trim()
        val phoneNumber = state.phoneNumber.trim().ifBlank { null }
        val displayName = state.displayName.trim()
        val password = state.password
        val confirmPassword = state.confirmPassword
        val country = state.country.trim()

        when {
            username.length < 3 -> {
                setError("Le nom d'utilisateur doit contenir au moins 3 caractères.")
                return@launch
            }

            email.isBlank() -> {
                setError("Entre ton adresse email.")
                return@launch
            }

            displayName.isBlank() -> {
                setError("Entre ton nom affiché.")
                return@launch
            }

            password.length < 8 -> {
                setError("Le mot de passe doit contenir au moins 8 caractères.")
                return@launch
            }

            password != confirmPassword -> {
                setError("Les mots de passe ne correspondent pas.")
                return@launch
            }

            country.isBlank() -> {
                setError("Entre ton pays.")
                return@launch
            }
        }

        _uiState.value = state.copy(
            isLoading = true,
            errorMessage = null,
        )

        try {
            repository.register(
                username = username,
                email = email,
                phoneNumber = phoneNumber,
                password = password,
                displayName = displayName,
                country = country,
                language = state.language,
            )

            repository.login(
                emailOrPhone = email,
                password = password,
            )

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isRegistered = true,
                errorMessage = null,
            )
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isRegistered = false,
                errorMessage = "Impossible de créer le compte. Vérifie tes informations.",
            )
        }
    }

    private fun setError(message: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            errorMessage = message,
        )
    }
}
