package com.myriam.projetfinal.screens.ProfileScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.Achievement.Achievement
import com.myriam.projetfinal.UserProfile.UserProfile
import com.myriam.projetfinal.R

class ProfileScreenViewModel : ViewModel() {

        val userProfile = UserProfile(
            username = "CodeNinja",
            level = 5,
            streakCount = 15,
            daysActive = 30,
            totalXp = 1250,
            profileImageRes = R.drawable.cpplogo,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#3F51B5")),
                Color(android.graphics.Color.parseColor("#7986CB"))
            )
        )

        // Initialize achievements
        val achievements = listOf(
            Achievement(
                id = "first_program",
                title = "First Program",
                category = "Beginner",
                description = "Wrote your first program",
                progress = "1/1",
                imageRes = R.drawable.ic_launcher_foreground,
                starsRes = R.drawable.fivestars,
                colors = listOf(
                    Color(android.graphics.Color.parseColor("#4CAF50")),
                    Color(android.graphics.Color.parseColor("#A5D6A7"))
                ),
                isUnlocked = true
            ),
            Achievement(
                id = "week_streak",
                title = "On Fire",
                category = "Consistency",
                description = "Maintained a 7-day streak",
                progress = "7/7",
                imageRes = R.drawable.ic_launcher_foreground,
                starsRes = R.drawable.fivestars,
                colors = listOf(
                    Color(android.graphics.Color.parseColor("#FF9800")),
                    Color(android.graphics.Color.parseColor("#FFCC80"))
                ),
                isUnlocked = true
            ),
            Achievement(
                id = "algorithm_master",
                title = "Algorithm Master",
                category = "Advanced",
                description = "Completed 10 algorithm challenges",
                progress = "2/10",
                imageRes = R.drawable.ic_launcher_foreground,
                starsRes = R.drawable.fivestars,
                colors = listOf(
                    Color(android.graphics.Color.parseColor("#2196F3")),
                    Color(android.graphics.Color.parseColor("#90CAF9"))
                ),
                isUnlocked = false
            )
        )
    }

