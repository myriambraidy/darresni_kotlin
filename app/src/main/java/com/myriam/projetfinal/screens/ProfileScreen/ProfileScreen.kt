package com.myriam.projetfinal.screens.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreenViewModel
import com.myriam.projetfinal.screens.ProfileScreen.components.*

@Composable
fun ProfileScreen(navController: NavController) {
    val vm: ProfileScreenViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        item {
            HeaderSectionProfile(
                userProfile = vm.userProfile,
                onSettingsClick = { navController.navigate("settings") }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            StreakSectionProfile(streakCount = vm.userProfile.streakCount)
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            StatsSectionProfile(userProfile = vm.userProfile)
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            AchievementsSectionProfile(achievements = vm.achievements)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
