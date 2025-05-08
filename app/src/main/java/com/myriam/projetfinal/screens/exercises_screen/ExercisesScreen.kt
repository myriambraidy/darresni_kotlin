package com.myriam.projetfinal.screens.exercises_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ScreenHeader
import com.myriam.projetfinal.screens.exercises_screen.components.CategoryTitle
import com.myriam.projetfinal.screens.exercises_screen.sections.HorizontalScrollSection

@Composable
fun ExercisesScreen(vm: ExerciseViewModel, nav: NavController) {
    val exercises by vm.exercises.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0505))
            .padding(16.dp)
            .padding(horizontal = 4.dp)
    ) {

        ScreenHeader(
            title = "Exercises",
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                CategoryTitle(
                    title = "Debugging Exercises",
                    onViewMoreClick = {
                        nav.navigate("codeSnippets")
                    }
                )
                HorizontalScrollSection(exercises = exercises.filter { it.exoType == "Debugging" }, vm = vm, nav = nav)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CategoryTitle(
                    title = "\"Explain this\"",
                    onViewMoreClick = {
                        nav.navigate("codeSnippets")
                    }
                )
                HorizontalScrollSection(exercises = exercises.filter { it.exoType == "Explain" }, vm = vm, nav = nav)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}