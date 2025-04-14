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
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F0F0F0")),
                Color(android.graphics.Color.parseColor("#388E3C"))
            )
        ),

        Exercise(
            title = "Data Structures",
            category = "Binary Tree Traversal",
            description = "Implement binary tree traversal in Java.",
            question = "public class BinaryTree {\n" +
                    "    static class Node {\n" +
                    "        int data;\n" +
                    "        Node left, right;\n" +
                    "        Node(int item) {\n" +
                    "            data = item;\n" +
                    "            left = right = null;\n" +
                    "        }\n" +
                    "    }\n" +
                    "    void inorder(Node root) {\n" +
                    "        if (root != null) {\n" +
                    "            inorder(root.left);\n" +
                    "            System.out.print(root.data + \" \");\n" +
                    "            inorder(root.right);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    public static void main(String[] args) {\n" +
                    "        BinaryTree tree = new BinaryTree();\n" +
                    "        Node root = new Node(1);\n" +
                    "        root.left = new Node(2);\n" +
                    "        root.right = new Node(3);\n" +
                    "        tree.inorder(root);\n" +
                    "    }\n" +
                    "}\n",
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
            category = "Heap vs Stack",
            description = "Explain the difference between heap and stack memory allocation.",
            question = "public class MemoryManagement {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        int a = 10; // stack memory\n" +
                    "        int[] arr = new int[5]; // heap memory\n" +
                    "        System.out.println(\"Stack memory: \" + a);\n" +
                    "        System.out.println(\"Heap memory: \" + Arrays.toString(arr));\n" +
                    "    }\n" +
                    "}\n",
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
//    var selectedExercise: Exercise? = null;
//
//    init {
//        exercises.value = originalExercises
//    }
//
//    fun filterExercises(query: String) {
//        val filteredList = if (query.isEmpty()) {
//            originalExercises
//        } else {
//            originalExercises.filter {
//                it.title.contains(query, ignoreCase = true) ||
//                        it.description.contains(query, ignoreCase = true)
//            }
//        }
//        exercises.value = filteredList
    }
