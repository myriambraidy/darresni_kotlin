package com.myriam.projetfinal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.myriam.projetfinal.screens.ExercisesScreen.components.SearchBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.Exercise.ExerciseList
import com.myriam.projetfinal.Exercise.ExerciseViewModel

@Composable
fun ExercisesScreen(exerciseViewModel: ExerciseViewModel) {
    val navController = rememberNavController()
    val viewmodel = ExerciseViewModel()

    NavHost(navController, startDestination = "exercises"){
        composable("exercises") {
            ExerciseMainContent(vm = viewmodel, nav = navController)
        }
        composable("exercise_details") {
            ExerciseDetailScreen(vm = viewmodel, navController = navController)
        }
    }

}

@Composable
fun ExerciseMainContent(vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Exercises",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, bottom = 4.dp)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Fire Icon",
                modifier = Modifier.size(30.dp)
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp
        )
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it
                vm.filterExercises(it)}
        )
        ExerciseList(viewModel = vm, navController = nav)
    }
}