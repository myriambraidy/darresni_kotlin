package com.myriam.projetfinal.navbar // Or your appropriate package

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreen
import com.myriam.projetfinal.screens.exercises_screen.ExercisesNav
import com.myriam.projetfinal.screens.home_screen.HomeScreen
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel



@Composable
fun MainScreen(appNav: NavHostController) {
    val innerNavController = rememberNavController()
    Log.d("UserRepository", "CAME HERE")
    val homeVM: HomeScreenViewModel = koinViewModel()
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        bottomBar = { CustomBottomNav(innerNavController, tabs) }
    ) { paddingValues ->

        NavHost(
            navController = innerNavController,
            startDestination = TabItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.Home.route) {
                HomeScreen(
                    vm = homeVM,
                    appNav = appNav
                )
            }

            composable(TabItem.Exercises.route) {
                ExercisesNav(
                    vm = koinViewModel()
                )
            }

            composable(TabItem.Profile.route) {
                ProfileScreen(
                    appNavController = appNav
                )
            }
        }
    }
}