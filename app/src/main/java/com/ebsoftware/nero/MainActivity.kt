package com.ebsoftware.nero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ebsoftware.nero.ui.NeroApp
import com.ebsoftware.nero.ui.rememberNeroAppState

class MainActivity :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeroApp(
                appState = rememberNeroAppState(),
            )
        }
    }
}