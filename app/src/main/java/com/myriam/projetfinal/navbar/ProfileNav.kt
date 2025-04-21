package com.myriam.projetfinal.screens.ProfileScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.Settings.SettingsScreen

@Composable
fun ProfileNav() {
    val profileNavController = rememberNavController()

    NavHost(
        navController = profileNavController,
        startDestination = "profileMain"
    ) {
        composable("profileMain") {
            ProfileScreen(navController = profileNavController)
        }
        composable("settings") {
            SettingsScreen(navController = profileNavController)
        }
    }
}
