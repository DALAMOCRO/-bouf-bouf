package com.boufbouf.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.boufbouf.app.core.network.RetrofitClient
import com.boufbouf.app.core.ui.BoufBoufTheme
import com.boufbouf.app.navigation.BoufBoufApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RetrofitClient.initialize(this)

        setContent {
            BoufBoufTheme {
                BoufBoufApp(context = this)
            }
        }
    }
}
