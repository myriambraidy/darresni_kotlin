package com.myriam.projetfinal.screens.ExercisesScreen

import HeaderSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.Exercise.ExerciseViewModel
import com.myriam.projetfinal.screens.ExercisesScreen.components.CategoryTitle
import com.myriam.projetfinal.screens.ExercisesScreen.components.CodeSnippets
import com.myriam.projetfinal.screens.ExercisesScreen.components.SearchBar

@Composable
fun MainExerciseScreen(vm: ExerciseViewModel, nav: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        HeaderSection(title= "Exercises",
            icon = Icons.Default.CheckCircle,
            onIconClick = {
                nav.navigate("dailychallenge")
            })

        Spacer(modifier = Modifier.height(8.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it
                vm.filterExercises(it)}
        )
        Spacer(modifier = Modifier.height(8.dp))

        CategoryTitle(
            title = "CodeSnippets",
            onViewMoreClick = {
                nav.navigate("codeSnippets")
            }
        )

        CodeSnippets(vm = vm)

        Spacer(modifier = Modifier.height(8.dp))

        CategoryTitle(
            title = "Latest Articles",
            onViewMoreClick = {
                nav.navigate("codeSnippets")
            }
        )

        CodeSnippets(vm = vm)




    }

}