package com.myriam.projetfinal.screens

import HeaderSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.myriam.projetfinal.screens.ExercisesScreen.components.SearchBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.DailyChallenge.DailyChallengeScreen
import com.myriam.projetfinal.DailyChallenge.DailyChallengeViewModel
import com.myriam.projetfinal.DailyChallenge.DailyChallengeWriteScreen
import com.myriam.projetfinal.Exercise.ExerciseList
import com.myriam.projetfinal.Exercise.ExerciseViewModel
import com.myriam.projetfinal.screens.ExercisesScreen.ExercisesScreen

@Composable
fun MainExercisesScreen(exerciseViewModel: ExerciseViewModel,
                    dailychallengevm : DailyChallengeViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main"){
        composable("main") {
            ExercisesScreen(vm= exerciseViewModel, nav = navController)
        }
        composable("codeSnippets") {
            CodeSnippets(vm = exerciseViewModel, nav= navController)
        }
        composable("exercise_details") {
            exerciseViewModel.selectedExercise?.let { it1 -> ExerciseDetailScreen(exo = it1, nav = navController) }
        }

        composable("dailychallenge") {
            DailyChallengeScreen(vm = dailychallengevm, nav= navController )
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

@Composable
fun CodeSnippets(vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)

    ) {
        HeaderSection(
            title = "Code Snippets",
            showBack = true,
            onBackClick = {
                nav.popBackStack()
            }
        )
        Spacer(modifier = Modifier.padding(3.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it
                vm.filterExercises(it)}
        )

        ExerciseList(viewModel = vm,
            navController = nav,
            modifier = Modifier.weight(1f)
        )
    }
}