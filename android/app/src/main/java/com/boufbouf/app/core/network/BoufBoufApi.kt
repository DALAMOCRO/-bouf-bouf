package com.boufbouf.app.core.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BoufBoufApi {

    @GET("api/v1/feed")
    suspend fun getFeed(): FeedResponse

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenResponse

    @POST("api/v1/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): UserResponse

    @GET("api/v1/auth/me")
    suspend fun getMe(): UserResponse
}
