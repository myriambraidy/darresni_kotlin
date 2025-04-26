package com.myriam.projetfinal

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.navbar.MainScreen
import com.myriam.projetfinal.screens.daily_challenge.DailyChallengeScreen
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    val dailyVm = DailyChallengeViewModel()

    NavHost(appNavController, startDestination = "main") {
        composable("main") {
            MainScreen(appNav = appNavController)
        }

        // change this later
        composable("daily_question") {
            DailyChallengeScreen(nav = appNavController, vm = dailyVm)
        }
    }
}