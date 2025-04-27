package com.myriam.projetfinal.daily_challenge

import com.myriam.projetfinal.data.models.Exercise
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R

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
            starsRes = R.drawable.cpplogo,
            accentColor = Color(android.graphics.Color.parseColor("#F0F0F0"))
        )
    )

    var userAnswer: String = ""

    fun getExercise(): Exercise {
        return originalExercises.first() // You can update this to return dynamically
    }
}