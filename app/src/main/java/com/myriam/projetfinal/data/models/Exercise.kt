package com.myriam.projetfinal.data.models

import androidx.compose.ui.graphics.Color

data class Exercise(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val contentLink: String?,
    val difficulty: String,
    val exoType: String,
    val lang: String,
    val hasAttempted: Boolean
)
