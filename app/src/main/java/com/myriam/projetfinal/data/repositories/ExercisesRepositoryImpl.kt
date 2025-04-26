package com.myriam.projetfinal.data.repositories

import androidx.compose.ui.graphics.Color
import com.myriam.projetfinal.R
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import com.myriam.projetfinal.data.models.Exercise

class ExercisesRepositoryImpl: ExercisesRepository {
    private val _exercises: List<Exercise> = listOf(
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
            accentColor = Color.Magenta
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
            accentColor = Color.Green
        ),
        Exercise(
            title = "Algorithms",
            category = "Sorting implementation",
            description = "Implement a sorting algorithm and debug it.",
            question = "public class SortingExample {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        int[] arr = {5, 2, 8, 1, 3};\n" +
                    "        Arrays.sort(arr);\n" +
                    "        System.out.println(\"Sorted array: \" + Arrays.toString(arr));\n" +
                    "    }\n" +
                    "}\n",
            id = "15",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            accentColor = Color.Cyan
        )
    )

    override fun getExercises(): List<Exercise> {
        return this._exercises
    }

    override fun getDevPick(): List<Exercise> {
        return this._exercises.subList(0, 1)
    }
}