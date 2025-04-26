package com.myriam.projetfinal.exercise

import androidx.compose.ui.graphics.Color

data class Exercise(
    val title: String,
    val category : String,
    val description : String,
    val question: String,
    val id: String,
    val imageRes: Int,
    val starsRes: Int,
    val accentColor: Color
)
