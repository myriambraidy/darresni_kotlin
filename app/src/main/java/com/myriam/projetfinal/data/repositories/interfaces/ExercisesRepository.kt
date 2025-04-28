package com.myriam.projetfinal.data.repositories.interfaces

import com.myriam.projetfinal.data.models.Exercise
import kotlinx.coroutines.flow.StateFlow

interface ExercisesRepository {
    val exercises: StateFlow<List<Exercise>>
    val devPick: StateFlow<List<Exercise>>
    fun getExercises(): List<Exercise>
    fun getDevPick(): List<Exercise>
    suspend fun getExercisesFromApi(token: String): List<Exercise>
}