package com.boufbouf.app.core.network

import retrofit2.http.GET

interface BoufBoufApi {

    @GET("api/v1/feed")
    suspend fun getFeed(): FeedResponse
}