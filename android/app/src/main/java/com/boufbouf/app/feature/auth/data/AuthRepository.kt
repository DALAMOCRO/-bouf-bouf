package com.boufbouf.app.feature.auth.data

import com.boufbouf.app.core.network.LoginRequest
import com.boufbouf.app.core.network.RetrofitClient
import com.boufbouf.app.core.storage.TokenStorage


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

    suspend fun getCurrentUser() =
        RetrofitClient.api.getMe()
}
