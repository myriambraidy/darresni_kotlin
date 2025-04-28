package com.myriam.projetfinal.exercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.components.ExerciseCard

@Composable
fun ExerciseList(
    viewModel: ExerciseViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val exercises by viewModel.exercises.collectAsState(initial = emptyList())
    LazyColumn (modifier = modifier
        .fillMaxSize()
    ){
        if (exercises.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Results Found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            }
        } else {
            items(exercises) { exercise ->
                ExerciseCard(
                    title = exercise.title,
                    description = exercise.description,
                    id = exercise.id.toString(),
                    lang = exercise.lang,
                    onClick = {
                        navController.navigate("exercise_details")
                        viewModel.selectedExercise = exercise
                    }
                )
            }
        }
    }
}