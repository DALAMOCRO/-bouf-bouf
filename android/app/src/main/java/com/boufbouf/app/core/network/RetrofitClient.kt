package com.boufbouf.app.core.network

import android.content.Context
import com.boufbouf.app.core.storage.TokenStorage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.100.141:8000/"

    private lateinit var tokenStorage: TokenStorage

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(tokenStorage)
            )
            .build()
    }

    fun initialize(context: Context) {
        tokenStorage = TokenStorage(context.applicationContext)
    }

    val api: BoufBoufApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoufBoufApi::class.java)
    }
}
