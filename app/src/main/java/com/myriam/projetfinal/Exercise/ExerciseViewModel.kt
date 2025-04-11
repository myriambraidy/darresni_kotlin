package com.myriam.projetfinal.Exercise

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R
import com.myriam.projetfinal.ui.theme.LightTextSecondaryColor

class ExerciseViewModel : ViewModel() {
    private val originalExercises = listOf(
        Exercise(
            title = "Java",
            description = "Explain the error",
            id = "43",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                LightTextSecondaryColor
            )
        ),
        Exercise(
            title = "Pointers",
            description = "Cpp - Debug and Fix",
            id = "23",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#00599C")),
                Color(android.graphics.Color.parseColor("#737373"))
            )
        ),

        Exercise(
            title = "Algorithms",
            description = "Sorting implementation",
            id = "15",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                Color(android.graphics.Color.parseColor("#388E3C"))
            )
        ),
        Exercise(
            title = "Data Structures",
            description = "Binary Tree Traversal",
            id = "29",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                Color(android.graphics.Color.parseColor("#1976D2"))
            )
        ),
        Exercise(
            title = "Memory Management",
            description = "Heap vs Stack",
            id = "37",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                Color(android.graphics.Color.parseColor("#E64A19"))
            )
        )
    )

    val exercises = MutableLiveData<List<Exercise>>()
    var selectedExercise: Exercise? = null;

    init {
        exercises.value = originalExercises
    }

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