package com.myriam.projetfinal.exercise

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.daily_challenge.DailyChallengeScreen
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.daily_challenge.DailyChallengeWriteScreen
import com.myriam.projetfinal.screens.ExerciseDetailScreen
import com.myriam.projetfinal.screens.exercises_screen.ExercisesScreen

@Composable
fun MainExercisesScreen(vm: ExerciseViewModel, dailyVM : DailyChallengeViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main"){
        composable("main") {
            ExercisesScreen(vm= vm, nav = navController)
        }
        composable("codeSnippets") {
            CodeSnippetsSection(vm = vm, nav= navController)
        }
        composable("exercise_details") {
            vm.selectedExercise?.let { it1 -> ExerciseDetailScreen(exo = it1, nav = navController) }
        }

        composable("daily_challenge") {
            DailyChallengeScreen(vm = dailyVM, nav= navController )
        }
        composable("startWriting") {
            var code by remember { mutableStateOf("") }

            DailyChallengeWriteScreen(
                nav = navController,
                code = code,
                onCodeChange = { code = it },
//                onBackClick = { navController.popBackStack() },
//                onSubmitClick = {  }
            )
        }
    }

}