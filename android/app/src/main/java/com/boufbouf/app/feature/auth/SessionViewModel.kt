package com.boufbouf.app.feature.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boufbouf.app.feature.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class SessionStatus {
    CHECKING,
    AUTHENTICATED,
    UNAUTHENTICATED,
}

class SessionViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _status = MutableStateFlow(SessionStatus.CHECKING)
    val status: StateFlow<SessionStatus> = _status.asStateFlow()

    fun checkSession() {
        if (_status.value != SessionStatus.CHECKING) {
            return
        }

        viewModelScope.launch {
            try {
                val token = repository.getStoredToken()

                Log.d(
                    "BoufBoufSession",
                    "Token présent: ${!token.isNullOrBlank()}"
                )

                if (token.isNullOrBlank()) {
                    Log.d(
                        "BoufBoufSession",
                        "Aucun token trouvé → utilisateur non authentifié"
                    )

                    _status.value = SessionStatus.UNAUTHENTICATED
                    return@launch
                }

                repository.getCurrentUser()

                Log.d(
                    "BoufBoufSession",
                    "Session authentifiée avec succès"
                )

                _status.value = SessionStatus.AUTHENTICATED

            } catch (exception: Exception) {

                Log.e(
                    "BoufBoufSession",
                    "Échec de vérification de session",
                    exception
                )

                repository.clearToken()

                _status.value = SessionStatus.UNAUTHENTICATED
            }
        }
    }
}