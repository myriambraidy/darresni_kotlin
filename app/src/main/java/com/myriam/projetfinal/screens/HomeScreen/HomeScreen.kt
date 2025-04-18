package com.myriam.projetfinal.screens.HomeScreen

import HeaderSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myriam.projetfinal.Exercise.ExerciseCard
import com.myriam.projetfinal.screens.HomeScreen.components.GraphSection
import com.myriam.projetfinal.screens.HomeScreen.components.StreakSection

@Composable
fun HomeScreen() {
    val vm: HomeScreenViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        HeaderSection(title= "Welcome", count = 3)
        Spacer(modifier = Modifier.height(32.dp))

        StreakSection()
        Spacer(modifier = Modifier.height(32.dp))

        GraphSection()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Devs’ pick of the day",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(vm.devsPick) { exercise ->
                ExerciseCard(
                    painter = painterResource(id = exercise.imageRes),
                    title = exercise.title,
                    description = exercise.description,
                    id = exercise.id,
                    starsPainter = painterResource(id = exercise.starsRes),
                    colors = exercise.colors,
                    onClick = {
//                        navController.navigate("exercise_details")
//                        viewModel.selectedExercise = exercise
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}