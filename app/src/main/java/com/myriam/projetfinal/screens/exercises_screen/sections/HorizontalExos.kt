package com.myriam.projetfinal.screens.exercises_screen.sections

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
import androidx.navigation.NavController
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.components.ExerciseCard

@Composable
fun HorizontalScrollSection(exercises: List<Exercise>, vm: ExerciseViewModel, nav: NavController) {

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
                        accentColor = exercise.accentColor,
                        onClick = {
                            vm.selectedExercise = exercise
                            nav.navigate("exercise_details")
                        },
                        modifier = Modifier
                            .width(310.dp)
                    )
                }
            }
        }
    }
}