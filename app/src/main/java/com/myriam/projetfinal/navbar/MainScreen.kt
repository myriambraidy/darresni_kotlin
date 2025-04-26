package com.myriam.projetfinal.navbar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.compose.*
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.exercise.ExerciseViewModel
import com.myriam.projetfinal.screens.home_screen.HomeScreen
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import com.myriam.projetfinal.exercise.MainExercisesScreen
import com.myriam.projetfinal.screens.profile_screen.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val exerciseVm = ExerciseViewModel()
    val dailyVm = DailyChallengeViewModel()
    val homeVm = HomeScreenViewModel()
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        bottomBar = { CustomBottomNav(navController, tabs) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TabItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.Home.route) { HomeScreen(vm=homeVm) }
            composable(TabItem.Exercises.route) { MainExercisesScreen(
                vm = exerciseVm,
                dailyVM = dailyVm
            )  }
            composable(TabItem.Profile.route) { ProfileScreen() }
        }
    }
}

