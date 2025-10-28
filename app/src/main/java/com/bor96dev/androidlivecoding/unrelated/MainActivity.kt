package com.bor96dev.androidlivecoding.unrelated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bor96dev.androidlivecoding.unrelated.ui.theme.AndroidLiveCodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLiveCodingTheme {

            }
        }
    }
}