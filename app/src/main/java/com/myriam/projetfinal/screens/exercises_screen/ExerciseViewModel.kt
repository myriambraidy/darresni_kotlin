package com.myriam.projetfinal.screens.exercises_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.data.repositories.ExercisesRepositoryImpl

class ExerciseViewModel(
    private val exoRepo: ExercisesRepositoryImpl
): ViewModel() {
    private val originalExercises = exoRepo.getExercises()

    val exercises = MutableLiveData<List<Exercise>>()
    var selectedExercise: Exercise? = null
    var searchQuery: String = ""

    init { exercises.value = originalExercises }

    fun filterExercises(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalExercises
        } else {
            originalExercises.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        exercises.value = filteredList
    }
}