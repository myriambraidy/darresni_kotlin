package com.myriam.projetfinal.screens.HomeScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.Exercise.Exercise
import com.myriam.projetfinal.R
import com.myriam.projetfinal.ui.theme.LightTextSecondaryColor

class
HomeScreenViewModel: ViewModel() {
    val devsPick = listOf(
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
        ),

        Exercise(
            title = "Pointers",
            category = "Cpp - Debug and Fix",
            description = "Debug and fix the following pointer-related issue.",
            question = "public class PointerExample {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        int a = 10;\n" +
                    "        int b = a;\n" +
                    "        b = 20;\n" +
                    "        System.out.println(\"a = \" + a + \", b = \" + b);\n" +
                    "    }\n" +
                    "}\n",
            id = "23",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#00599C")),
                Color(android.graphics.Color.parseColor("#737373"))
            )
        ),
    )


}