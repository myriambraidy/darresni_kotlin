package com.myriam.projetfinal.data.models

import androidx.compose.ui.graphics.Color

data class User(
    val id: String,
    val username: String,
    val email: String? = null,
    val streak: Int,
    val isAdmin: Boolean = false,
)