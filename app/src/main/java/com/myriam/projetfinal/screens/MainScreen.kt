package com.myriam.projetfinal.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.compose.*
import com.myriam.projetfinal.navbar.CustomBottomNav
import com.myriam.projetfinal.navbar.TabItem
import com.myriam.projetfinal.screens.HomeScreen.HomeScreen
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        bottomBar = { CustomBottomNav(navController, tabs) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TabItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.Home.route) { HomeScreen() }
            composable(TabItem.Exercises.route) { ExercisesScreen() }
            composable(TabItem.Profile.route) { ProfileScreen() }
        }
    }
}

