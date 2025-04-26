package com.myriam.projetfinal.navbar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.compose.*
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.ExercisesNav
import com.myriam.projetfinal.screens.home_screen.HomeScreen
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import com.myriam.projetfinal.screens.profile_screen.ProfileScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val homeVM: HomeScreenViewModel = koinViewModel()
    val exerciseVM: ExerciseViewModel = koinViewModel()
    val dailyVm = DailyChallengeViewModel()
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        bottomBar = { CustomBottomNav(navController, tabs) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TabItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.Home.route) { HomeScreen(vm=homeVM) }
            composable(TabItem.Exercises.route) { ExercisesNav(
                vm = exerciseVM,
                dailyVM = dailyVm
            )  }
            composable(TabItem.Profile.route) { ProfileScreen() }
        }
    }
}