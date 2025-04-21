package com.myriam.projetfinal.Achievement

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R

class AchievementViewModel : ViewModel() {
    private val originalAchievements = listOf(
        Achievement(
            id = "A1",
            title = "First Steps",
            category = "Beginner",
            description = "Complete your first coding challenge",
            progress = "1/1",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#4CAF50")),
                Color(android.graphics.Color.parseColor("#A5D6A7"))
            ),
            isUnlocked = true
        ),

        Achievement(
            id = "A2",
            title = "Code Streaker",
            category = "Consistency",
            description = "Maintain a 7-day streak",
            progress = "5/7",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#2196F3")),
                Color(android.graphics.Color.parseColor("#90CAF9"))
            ),
            isUnlocked = false
        ),

        Achievement(
            id = "A3",
            title = "Algorithm Master",
            category = "Advanced",
            description = "Complete 10 algorithm challenges",
            progress = "3/10",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#FF9800")),
                Color(android.graphics.Color.parseColor("#FFCC80"))
            ),
            isUnlocked = false
        ),

        Achievement(
            id = "A4",
            title = "Bug Hunter",
            category = "Debugging",
            description = "Fix 15 bugs in various code snippets",
            progress = "7/15",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#9C27B0")),
                Color(android.graphics.Color.parseColor("#CE93D8"))
            ),
            isUnlocked = false
        ),

        Achievement(
            id = "A5",
            title = "Code Contributor",
            category = "Community",
            description = "Share your code solutions with others",
            progress = "2/5",
            imageRes = R.drawable.cpplogo,
            starsRes = R.drawable.fivestars,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#F44336")),
                Color(android.graphics.Color.parseColor("#EF9A9A"))
            ),
            isUnlocked = false
        )
    )

    val achievements = MutableLiveData<List<Achievement>>()
    var selectedAchievement: Achievement? = null

    init {
        achievements.value = originalAchievements
    }

    fun filterAchievements(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalAchievements
        } else {
            originalAchievements.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        achievements.value = filteredList
    }
}