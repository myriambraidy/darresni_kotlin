package com.myriam.projetfinal.Exercise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ExerciseList(
    viewModel: ExerciseViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val exercises by viewModel.exercises.observeAsState(emptyList())

    LazyColumn (modifier = modifier
        .fillMaxSize()
    ){
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





