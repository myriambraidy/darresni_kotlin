package com.myriam.projetfinal.screens.exercises_screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ScreenHeader
import com.myriam.projetfinal.exercise.ExerciseList
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.components.SearchBar

@Composable
fun MoreExercisesSection(title: String, vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0505))
            .padding(16.dp)
            .padding(horizontal = 16.dp)

    ) {
        ScreenHeader(
            title = title,
            showBack = true,
            onBackClick = {
                nav.popBackStack()
            }
        )
        Spacer(modifier = Modifier.padding(3.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it
                vm.filterExercises(it)}
        )

        ExerciseList(viewModel = vm,
            navController = nav,
            modifier = Modifier.weight(1f)
        )
    }
}