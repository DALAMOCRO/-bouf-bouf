package com.boufbouf.app.feature.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boufbouf.app.core.storage.TokenStorage
import com.boufbouf.app.feature.auth.data.AuthRepository

class AuthViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {

    private val repository = AuthRepository(
        tokenStorage = TokenStorage(context.applicationContext)
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}
