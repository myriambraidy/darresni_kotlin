package com.myriam.projetfinal.navbar // Or your appropriate package

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController // Use NavHostController for clarity
import androidx.navigation.compose.*

// Import necessary screens and ViewModels
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreen // Import ProfileScreen
import com.myriam.projetfinal.screens.exercises_screen.ExercisesNav
import com.myriam.projetfinal.screens.home_screen.HomeScreen
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

// Make sure TabItem and CustomBottomNav are defined/imported correctly
// object TabItem { object Home : Screen("home", "Home")... etc }
// @Composable fun CustomBottomNav(...) { ... }

@Composable
fun MainScreen(appNav: NavHostController) { // Renamed NavController to NavHostController
    // This NavController is for switching between the main bottom tab sections (Home, Exercises, Profile)
    val innerNavController = rememberNavController()

    // Get ViewModels needed by screens within this NavHost via Koin
    val homeVM: HomeScreenViewModel = koinViewModel()
    // val exerciseVM: ExerciseViewModel = koinViewModel() // Keep if ExercisesNav needs it passed here

    // Define the tabs for the bottom navigation
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        // Pass the innerNavController to the bottom bar to handle tab selection
        bottomBar = { CustomBottomNav(innerNavController, tabs) }
    ) { paddingValues ->
        // This NavHost manages the content displayed *above* the bottom bar,
        // switching between Home, Exercises, and Profile content.
        NavHost(
            navController = innerNavController, // Use the inner controller for tab content
            startDestination = TabItem.Home.route, // Default tab to show
            modifier = Modifier.padding(paddingValues) // Apply padding from Scaffold
        ) {
            // Home Tab Content
            composable(TabItem.Home.route) {
                HomeScreen(
                    vm = homeVM,
                    appNav = appNav // Pass outer controller if Home needs to trigger app-level nav
                )
            }

            // Exercises Tab Content
            composable(TabItem.Exercises.route) {
                // Assuming ExercisesNav manages its own ViewModel injection if needed
                ExercisesNav(
                    vm = koinViewModel() // Inject ExerciseViewModel here or inside ExercisesNav
                )
            }

            // Profile Tab Content - MODIFIED
            composable(TabItem.Profile.route) {
                // Removed ProfileNav and the key logic.
                // Directly display the ProfileScreen.
                // ProfileScreen will inject its own ViewModel using koinViewModel().
                // Pass the main appNavController for the logout functionality.
                ProfileScreen(
                    appNavController = appNav
                )
            }
        }
    }
}