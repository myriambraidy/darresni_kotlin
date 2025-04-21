package com.myriam.projetfinal.screens.ExercisesScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myriam.projetfinal.Exercise.ExerciseCard
import com.myriam.projetfinal.Exercise.ExerciseViewModel

@Composable
fun CodeSnippets(vm : ExerciseViewModel) {

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(vm.originalExercises.chunked(2)) { pair ->
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                pair.forEach { exercise ->
                    ExerciseCard(
                        painter = painterResource(id = exercise.imageRes),
                        title = exercise.title,
                        description = exercise.description,
                        id = exercise.id,
                        starsPainter = painterResource(id = exercise.starsRes),
                        colors = exercise.colors,
                        onClick = {},
                        modifier = Modifier
                            .width(310.dp) // or adjust based on your screen
                    )
                }
            }
        }
    }
}