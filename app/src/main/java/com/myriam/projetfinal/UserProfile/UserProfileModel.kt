package com.myriam.projetfinal.UserProfile

import androidx.compose.ui.graphics.Color

data class UserProfile(
    val username: String,
    val level: Int,
    val streakCount: Int,
    val daysActive: Int,
    val totalXp: Int,
    val profileImageRes: Int,
    val colors: List<Color>
)