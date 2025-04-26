package com.myriam.projetfinal.screens.ExercisesScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.Exercise.Exercise
import com.myriam.projetfinal.Exercise.ExerciseCard
import com.myriam.projetfinal.Exercise.ExerciseViewModel

@Composable
fun ExosHorizontalScrollSection(exercises: List<Exercise>, nav : NavController) {

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(exercises.chunked(2)) { pair ->
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
                        accentColor = exercise.colors.first(),
                        onClick = {
//                            vm.selectedExercise = exercise
//                            nav.navigate("exercise_details")
                        },
                        modifier = Modifier
                            .width(310.dp)
                    )
                }
            }
        }
    }
}