package com.myriam.projetfinal.data.models

// Define the structure for a single history entry
data class History(
    val id: String,
    val description: String,
    val date: String,
    val success: Boolean // Example: Track if an attempt was successful
)