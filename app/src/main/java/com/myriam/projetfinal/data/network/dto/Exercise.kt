package com.myriam.projetfinal.data.network.dto

data class ExerciseResponse(
    val status: String,
    val message: String,
    val data: List<ExerciseDto>
)

// DTO for a single exercise item in the response
data class ExerciseDto(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val content_link: String,
    val difficulty: String,
    val exo_type: String,
    val lang: String,
    val hasAttempted: Boolean
)