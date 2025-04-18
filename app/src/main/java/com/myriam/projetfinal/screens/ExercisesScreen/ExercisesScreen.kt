package com.myriam.projetfinal.screens

import HeaderSection
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)

    ) {
        HeaderSection(title = "Exercises", icon = Icons.Default.Favorite)
        Spacer(modifier = Modifier.padding(3.dp))
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it
                vm.filterExercises(it)}
        )
        ExerciseList(viewModel = vm, navController = nav)
    }
}