package com.myriam.projetfinal.Exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ExerciseList(
    viewModel: ExerciseViewModel = viewModel(),
    navController: NavController
) {
    val exercises by viewModel.exercises.observeAsState(emptyList())

    LazyColumn {
        items(exercises) { exercise ->
            ExerciseCard(
                painter = painterResource(id = exercise.imageRes),
                title = exercise.title,
                description = exercise.description,
                id = exercise.id,
                starsPainter = painterResource(id = exercise.starsRes),
                colors = exercise.colors,
                onClick = {
                    navController.navigate("exercise_details")
                    viewModel.selectedExercise = exercise
                }
            )
        }
    }
}





