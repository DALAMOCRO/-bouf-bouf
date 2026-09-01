package com.boufbouf.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.boufbouf.app.core.ui.BoufBoufTheme
import com.boufbouf.app.navigation.BoufBoufApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoufBoufTheme {
                BoufBoufApp()
            }
        }
    }
}
