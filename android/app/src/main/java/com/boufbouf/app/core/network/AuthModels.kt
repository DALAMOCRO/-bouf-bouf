package com.boufbouf.app.core.network

data class LoginRequest(
    val email_or_phone: String,
    val password: String
)

data class TokenResponse(
    val access_token: String,
    val token_type: String
)

data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val phone_number: String?,
    val display_name: String,
    val bio: String,
    val avatar_url: String?,
    val country: String,
    val language: String
)
