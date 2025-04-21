package com.myriam.projetfinal.UserProfile

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.R

class UserProfileViewModel : ViewModel() {
    val userProfile = MutableLiveData<UserProfile>()

    init {
        // In a real application, this data would come from a repository or database
        userProfile.value = UserProfile(
            username = "CodeNinja",
            level = 7,
            streakCount = 12,
            daysActive = 27,
            totalXp = 3450,
            profileImageRes = R.drawable.cpplogo,
            colors = listOf(
                Color(android.graphics.Color.parseColor("#3F51B5")),
                Color(android.graphics.Color.parseColor("#7986CB"))
            )
        )
    }

    fun updateUsername(newUsername: String) {
        val currentProfile = userProfile.value
        currentProfile?.let {
            userProfile.value = it.copy(username = newUsername)
        }
    }

    fun incrementStreak() {
        val currentProfile = userProfile.value
        currentProfile?.let {
            userProfile.value = it.copy(streakCount = it.streakCount + 1)
        }
    }

    fun addXp(additionalXp: Int) {
        val currentProfile = userProfile.value
        currentProfile?.let {
            userProfile.value = it.copy(totalXp = it.totalXp + additionalXp)
            // Calculate level based on XP logic could be added here
        }
    }
}
