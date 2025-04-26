package com.myriam.projetfinal.exercise

import SectionTitle
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
import com.myriam.projetfinal.screens.exercises_screen.components.SearchBar

@Composable
fun CodeSnippetsSection(vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)

    ) {
        SectionTitle(
            title = "Code Snippets",
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