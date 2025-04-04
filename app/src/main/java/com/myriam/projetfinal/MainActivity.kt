package com.myriam.projetfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.myriam.projetfinal.screens.MainScreen
import com.myriam.projetfinal.ui.theme.ProjetFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetFinalTheme {
                MainScreen()

            }
        }
    }
}


