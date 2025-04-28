package com.myriam.projetfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import com.myriam.projetfinal.di.appModule
import com.myriam.projetfinal.di.networkModule
import com.myriam.projetfinal.ui.theme.ProjetFinalTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        super.onCreate(savedInstanceState)

        startKoin{
            androidContext(this@MainActivity)
            modules(appModule, networkModule)
        }

        setContent {
            ProjetFinalTheme(darkTheme = true) {
                KoinAndroidContext {
                    AppNavigation()
                }
            }
        }
    }
}
