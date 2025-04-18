package com.myriam.projetfinal.DailyChallenge

import com.myriam.projetfinal.Exercise.Exercise

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R
import com.myriam.projetfinal.ui.theme.LightTextSecondaryColor

class DailyChallengeViewModel : ViewModel() {
    private val originalExercises = listOf(
        Exercise(
            title = "Java",
            category = "Explain the error",
            description = "Explain the error",
            question = "public class HelloWorld {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        // Print Hello, World! to the console\n" +
                    "        System.out.println(\"Hello, World!\");\n" +
                    "    }\n" +
                    "}\n",
            id = "43",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                LightTextSecondaryColor
            )
        )
    )

    fun getExercise(): Exercise {
        return originalExercises.first() // You can update this to return dynamically
    }
}

