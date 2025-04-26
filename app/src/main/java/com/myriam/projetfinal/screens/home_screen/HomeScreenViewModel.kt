package com.myriam.projetfinal.screens.home_screen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.exercise.Exercise
import com.myriam.projetfinal.R

class HomeScreenViewModel: ViewModel() {
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
            accentColor = Color(android.graphics.Color.parseColor("#F0F0F0"))
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
            accentColor = Color(android.graphics.Color.parseColor("#00599C"))
        ),
    )
    val devPick = MutableLiveData<List<Exercise>>()
    var selectedDevPick: Exercise? = null

    init {
        devPick.value = devsPick
    }

    fun filterDevsPick(query: String) {
        val filteredList = if (query.isEmpty()) {
            devsPick
        } else {
            devsPick.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        devPick.value = filteredList
    }
}