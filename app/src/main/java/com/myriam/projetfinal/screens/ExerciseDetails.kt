package com.myriam.projetfinal.screens

import HeaderSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.Exercise.ExerciseViewModel

@Composable
fun ExerciseDetailScreen(vm: ExerciseViewModel, nav: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)

    ) {
        vm.selectedExercise?.title?.let {
            HeaderSection(
                title = it,
                showBack = true,
                onBackClick = {
                    nav.popBackStack()
                }
            )
        }



    }
}