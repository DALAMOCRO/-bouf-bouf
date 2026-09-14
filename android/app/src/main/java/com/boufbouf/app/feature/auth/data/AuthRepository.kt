package com.boufbouf.app.feature.auth.data

import com.boufbouf.app.core.network.LoginRequest
import com.boufbouf.app.core.network.RegisterRequest
import com.boufbouf.app.core.network.RetrofitClient
import com.boufbouf.app.core.network.UserResponse
import com.boufbouf.app.core.storage.TokenStorage
import kotlinx.coroutines.flow.first

class AuthRepository(
    private val tokenStorage: TokenStorage
) {

    suspend fun login(
        emailOrPhone: String,
        password: String
    ) {
        val response = RetrofitClient.api.login(
            LoginRequest(
                email_or_phone = emailOrPhone,
                password = password
            )
        )

        tokenStorage.saveToken(response.access_token)
    }

    suspend fun register(
        username: String,
        email: String,
        phoneNumber: String?,
        password: String,
        displayName: String,
        country: String,
        language: String
    ): UserResponse {
        return RetrofitClient.api.register(
            RegisterRequest(
                username = username,
                email = email,
                phone_number = phoneNumber,
                password = password,
                display_name = displayName,
                country = country,
                language = language
            )
        )
    }

    suspend fun getCurrentUser(): UserResponse {
        return RetrofitClient.api.getMe()
    }

    suspend fun getStoredToken(): String? {
        return tokenStorage.token.first()
    }

    suspend fun clearToken() {
        tokenStorage.clearToken()
    }
}