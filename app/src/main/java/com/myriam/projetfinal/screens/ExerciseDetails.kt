package com.myriam.projetfinal.screens

import SectionTitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.exercise.Exercise

@Composable
fun ExerciseDetailScreen(exo: Exercise, nav: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)

    ) {
        SectionTitle(
            title = exo.title,
            showBack = true,
            onBackClick = {
                nav.popBackStack()
            }
        )
    }
}