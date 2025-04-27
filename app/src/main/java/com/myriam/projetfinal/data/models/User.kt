package com.myriam.projetfinal.data.models

import androidx.compose.ui.graphics.Color

data class User(
    val username: String,
    val email: String? = null,
    val level: Int = 1,
    val daysActive: Int = 0,
    val totalXp: Int = 0,
    val profileImageRes: Int? = null,
    val colors: List<Color> = emptyList(),
    val history: List<History> = emptyList()
)