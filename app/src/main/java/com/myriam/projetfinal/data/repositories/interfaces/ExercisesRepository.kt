package com.myriam.projetfinal.data.repositories.interfaces

import com.myriam.projetfinal.data.models.Exercise

interface ExercisesRepository {
    fun getExercises(): List<Exercise>
    fun getDevPick(): List<Exercise>
}