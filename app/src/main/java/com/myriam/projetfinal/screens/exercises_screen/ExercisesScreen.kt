package com.myriam.projetfinal.screens.exercises_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ScreenHeader
import com.myriam.projetfinal.screens.exercises_screen.components.CategoryTitle
import com.myriam.projetfinal.screens.exercises_screen.sections.HorizontalScrollSection
import com.myriam.projetfinal.screens.exercises_screen.components.SearchBar

@Composable
fun ExercisesScreen(vm: ExerciseViewModel, nav: NavController) {
    val exercises by vm.exercises.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        ScreenHeader(
            title = "Exercises",
            icon = Icons.Default.CheckCircle,
            onIconClick = {
                nav.navigate("daily_challenge")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SearchBar(
            query = vm.searchQuery,
            onQueryChange = {
                vm.searchQuery = it
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

                HorizontalScrollSection(exercises = exercises ?: emptyList(), vm = vm, nav = nav)

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

                HorizontalScrollSection(exercises = exercises ?: emptyList(), vm = vm, nav = nav)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}