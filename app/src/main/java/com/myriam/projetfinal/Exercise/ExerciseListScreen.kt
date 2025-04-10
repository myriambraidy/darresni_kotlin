package com.myriam.projetfinal.Exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExerciseScreen(viewModel: ExerciseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val exercises by viewModel.exercises.observeAsState(emptyList())

    LazyColumn {
        items(exercises) { exercise ->
            ExerciseCard(
                painter = painterResource(id = exercise.imageRes),
                title = exercise.title,
                description = exercise.description,
                id = exercise.id,
                starsPainter = painterResource(id = exercise.starsRes),
                colors = exercise.colors
            )
        }
    }
}





