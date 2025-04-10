package com.myriam.projetfinal.Exercise

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R
import com.myriam.projetfinal.ui.theme.LightTextSecondaryColor

class ExerciseViewModel : ViewModel() {
    val exercises = MutableLiveData<List<Exercise>>()
    init {
        exercises.value = listOf(
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
                title = "Pointers",
                description = "Cpp - Debug and Fix",
                id = "23",
                imageRes = R.drawable.cpplogo,
                starsRes = R.drawable.fivestars,
                colors = listOf(
                    Color(android.graphics.Color.parseColor("#00599C")),
                    Color(android.graphics.Color.parseColor("#737373"))
                )
            )
        )

    }
}
