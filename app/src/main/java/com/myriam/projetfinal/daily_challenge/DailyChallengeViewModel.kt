package com.myriam.projetfinal.daily_challenge

import com.myriam.projetfinal.data.models.Exercise
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R

class DailyChallengeViewModel : ViewModel() {
    private val originalExercises = listOf(
        Exercise(
            title = "Java",
            exoType = "Explain the error",
            description = "Explain the error",
            content = "public class HelloWorld {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        // Print Hello, World! to the console\n" +
                    "        System.out.println(\"Hello, World!\");\n" +
                    "    }\n" +
                    "}\n",
            id = 43,
            contentLink = "",
            lang = "cpp",
            difficulty = "3",
            hasAttempted = false
        )
    )

    var userAnswer: String = ""

    fun getExercise(): Exercise {
        return originalExercises.first() // You can update this to return dynamically
    }
}