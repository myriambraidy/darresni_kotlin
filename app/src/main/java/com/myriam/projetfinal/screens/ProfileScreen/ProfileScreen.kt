package com.myriam.projetfinal.screens.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import com.myriam.projetfinal.screens.ProfileScreen.components.AchievementsSectionProfile
import com.myriam.projetfinal.screens.ProfileScreen.components.HeaderSectionProfile
import com.myriam.projetfinal.screens.ProfileScreen.components.StatsSectionProfile
import com.myriam.projetfinal.screens.ProfileScreen.components.StreakSectionProfile

@Composable
fun ProfileScreen() {
    val vm: ProfileScreenViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with avatar and username
        HeaderSectionProfile(
            userProfile = vm.userProfile,
            onSettingsClick = { /* Navigate to settings */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Streak card (similar to Duolingo flame)
        StreakSectionProfile(streakCount = vm.userProfile.streakCount)

        Spacer(modifier = Modifier.height(32.dp))

        // Stats section (XP, days active)
        StatsSectionProfile(userProfile = vm.userProfile)

        Spacer(modifier = Modifier.height(32.dp))

        // Achievements section
        AchievementsSectionProfile(achievements = vm.achievements)

        Spacer(modifier = Modifier.height(32.dp))
    }
}