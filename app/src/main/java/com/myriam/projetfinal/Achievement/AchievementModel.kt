package com.myriam.projetfinal.Achievement

import androidx.compose.ui.graphics.Color

data class Achievement(
    val title: String,
    val category: String,
    val description: String,
    val progress: String,
    val id: String,
    val imageRes: Int,
    val starsRes: Int,
    val colors: List<Color>,
    val isUnlocked: Boolean
)