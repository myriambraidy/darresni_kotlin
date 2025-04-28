package com.myriam.projetfinal.screens.exercises_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExerciseViewModel(
    private val exoRepo: ExercisesRepository
) : ViewModel() {
    // Observe the StateFlow of all exercises from the repository
    private val _allExercises = exoRepo.exercises
    val allExercises: StateFlow<List<Exercise>> = _allExercises

    // State to hold the filtered list of exercises for the UI
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    var selectedExercise: Exercise? = null
    var searchQuery: String = ""

    init {
        // Initialize the _exercises StateFlow with the initial value from the repository
        _exercises.value = exoRepo.exercises.value

        // Observe changes to allExercises from the repository and update the filtered list
        viewModelScope.launch {
            exoRepo.exercises.collectLatest { fetchedExercises ->
                _exercises.value = filterList(fetchedExercises, searchQuery)
            }
        }
    }

    fun filterExercises(query: String) {
        searchQuery = query
        _exercises.value = filterList(_allExercises.value, query)
    }

    private fun filterList(list: List<Exercise>, query: String): List<Exercise> {
        return if (query.isEmpty()) {
            list
        } else {
            list.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
    }
}