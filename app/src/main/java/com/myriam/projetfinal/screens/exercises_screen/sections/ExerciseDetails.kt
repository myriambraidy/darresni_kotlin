package com.myriam.projetfinal.screens.exercises_screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ScreenHeader
import com.myriam.projetfinal.data.models.Exercise

@Composable
fun ExerciseDetails(exo: Exercise, nav: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        ScreenHeader(
            title = exo.title,
            showBack = true,
            onBackClick = {
                nav.popBackStack()
            }
        )
    }
}