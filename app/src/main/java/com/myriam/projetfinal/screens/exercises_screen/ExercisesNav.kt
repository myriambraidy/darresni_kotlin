package com.myriam.projetfinal.screens.exercises_screen

import androidx.compose.runtime.Composable
import com.myriam.projetfinal.screens.exercises_screen.sections.MoreExercisesSection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.screens.exercises_screen.sections.ExerciseDetails

@Composable
fun ExercisesNav(vm: ExerciseViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main"){
        composable("main") {
            ExercisesScreen(vm= vm, nav = navController)
        }
        composable("codeSnippets") {
            MoreExercisesSection(title = "Code Snippets", vm = vm, nav= navController)
        }
        composable("exercise_details") {
            vm.selectedExercise?.let { it1 -> ExerciseDetails(exo = it1, nav = navController) }
        }

    }
}