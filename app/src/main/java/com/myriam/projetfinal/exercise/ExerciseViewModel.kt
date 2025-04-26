package com.myriam.projetfinal.exercise

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R

class ExerciseViewModel : ViewModel() {
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
            accentColor = Color(android.graphics.Color.parseColor("#737373"))
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
            accentColor = Color(android.graphics.Color.parseColor("#737373"))
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
            accentColor = Color(android.graphics.Color.parseColor("#737373"))
        )
    )
    val exercises = MutableLiveData<List<Exercise>>()
    var selectedExercise: Exercise? = null

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