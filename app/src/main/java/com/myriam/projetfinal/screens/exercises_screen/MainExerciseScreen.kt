package com.myriam.projetfinal.screens.exercises_screen

import SectionTitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.exercise.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.components.CategoryTitle
import com.myriam.projetfinal.screens.exercises_screen.components.ExosHorizontalScrollSection
import com.myriam.projetfinal.screens.exercises_screen.components.SearchBar

@Composable
fun ExercisesScreen(vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val exercises by vm.exercises.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        // Fixed header section
        SectionTitle(
            title = "Exercises",
            icon = Icons.Default.CheckCircle,
            onIconClick = {
                nav.navigate("daily_challenge")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
                vm.filterExercises(it)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable content in LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Code Snippets section
            item {
                CategoryTitle(
                    title = "CodeSnippets",
                    onViewMoreClick = {
                        nav.navigate("codeSnippets")
                    }
                )

                ExosHorizontalScrollSection(exercises = exercises ?: emptyList(), nav = nav)

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Latest Articles section
            item {
                CategoryTitle(
                    title = "Latest Articles",
                    onViewMoreClick = {
                        nav.navigate("codeSnippets")
                    }
                )

                ExosHorizontalScrollSection(exercises = exercises ?: emptyList(), nav = nav)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}