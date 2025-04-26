package com.myriam.projetfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.myriam.projetfinal.di.appModule
import com.myriam.projetfinal.navbar.MainScreen
import com.myriam.projetfinal.ui.theme.ProjetFinalTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            ProjetFinalTheme {
                MainScreen()
            }
        }
    }
}