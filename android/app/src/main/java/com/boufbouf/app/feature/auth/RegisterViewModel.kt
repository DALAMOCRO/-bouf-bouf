package com.boufbouf.app.feature.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boufbouf.app.feature.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(
            username = value,
            errorMessage = null
        )
    }

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(
            email = value,
            errorMessage = null
        )
    }

    fun updatePhoneNumber(value: String) {
        _uiState.value = _uiState.value.copy(
            phoneNumber = value,
            errorMessage = null
        )
    }

    fun updateDisplayName(value: String) {
        _uiState.value = _uiState.value.copy(
            displayName = value,
            errorMessage = null
        )
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            errorMessage = null
        )
    }

    fun updateConfirmPassword(value: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = value,
            errorMessage = null
        )
    }

    fun updateCountry(value: String) {
        _uiState.value = _uiState.value.copy(
            country = value,
            errorMessage = null
        )
    }

    fun updateLanguage(value: String) {
        _uiState.value = _uiState.value.copy(
            language = value,
            errorMessage = null
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
        val language = state.language

        when {
            username.length < 3 -> {
                setError("Le nom d'utilisateur doit contenir au moins 3 caractères.")
                return@launch
            }

            email.isBlank() -> {
                setError("L'email est obligatoire.")
                return@launch
            }

            displayName.isBlank() -> {
                setError("Le nom affiché est obligatoire.")
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
                setError("Le pays est obligatoire.")
                return@launch
            }
        }

        _uiState.value = state.copy(
            isLoading = true,
            errorMessage = null
        )

        Log.d(
            "BoufBoufAuth",
            "Début inscription - username=$username, email=$email, language=$language"
        )

        // ÉTAPE 1 : inscription
        try {
            repository.register(
                username = username,
                email = email,
                phoneNumber = phoneNumber,
                password = password,
                displayName = displayName,
                country = country,
                language = language
            )

            Log.d(
                "BoufBoufAuth",
                "REGISTER OK - compte créé avec succès"
            )

        } catch (exception: HttpException) {
            val status = exception.code()
            val body = exception.response()
                ?.errorBody()
                ?.string()
                .orEmpty()

            Log.e(
                "BoufBoufAuth",
                "REGISTER HTTP $status - $body"
            )

            val message = when (status) {
                409 -> "Ce nom d'utilisateur ou cet email est déjà utilisé."
                422 -> "Les informations envoyées sont invalides."
                else -> "Erreur serveur lors de l'inscription ($status)."
            }

            setError(message)
            return@launch

        } catch (exception: IOException) {
            Log.e(
                "BoufBoufAuth",
                "REGISTER NETWORK ERROR - ${exception.message}",
                exception
            )

            setError("Impossible de contacter le serveur.")
            return@launch

        } catch (exception: Exception) {
            Log.e(
                "BoufBoufAuth",
                "REGISTER ERROR - ${exception::class.java.name}: ${exception.message}",
                exception
            )

            setError("Une erreur est survenue pendant l'inscription.")
            return@launch
        }

        // ÉTAPE 2 : connexion automatique
        try {
            repository.login(
                emailOrPhone = email,
                password = password
            )

            Log.d(
                "BoufBoufAuth",
                "LOGIN OK - token reçu et sauvegardé"
            )

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isRegistered = true,
                errorMessage = null
            )

        } catch (exception: HttpException) {
            val status = exception.code()
            val body = exception.response()
                ?.errorBody()
                ?.string()
                .orEmpty()

            Log.e(
                "BoufBoufAuth",
                "LOGIN HTTP $status - $body"
            )

            val message = when (status) {
                401 -> "Compte créé, mais la connexion automatique a échoué."
                422 -> "Compte créé, mais les données de connexion sont invalides."
                else -> "Compte créé, mais erreur de connexion ($status)."
            }

            setError(message)

        } catch (exception: IOException) {
            Log.e(
                "BoufBoufAuth",
                "LOGIN NETWORK ERROR - ${exception.message}",
                exception
            )

            setError("Compte créé, mais le serveur est inaccessible.")

        } catch (exception: Exception) {
            Log.e(
                "BoufBoufAuth",
                "LOGIN ERROR - ${exception::class.java.name}: ${exception.message}",
                exception
            )

            setError("Compte créé, mais une erreur est survenue lors de la connexion.")
        }
    }

    private fun setError(message: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRegistered = false,
            errorMessage = message
        )
    }
}
