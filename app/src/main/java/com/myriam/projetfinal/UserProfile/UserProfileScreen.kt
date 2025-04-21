package com.myriam.projetfinal.UserProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.myriam.projetfinal.Achievement.AchievementList
import com.myriam.projetfinal.Achievement.AchievementViewModel

@Composable
fun UserProfileScreen(
    userProfileViewModel: UserProfileViewModel = viewModel(),
    achievementViewModel: AchievementViewModel = viewModel(),
    navController: NavController
) {
    val userProfile by userProfileViewModel.userProfile.observeAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        userProfile?.let { profile ->
            UserProfileCard(
                profilePainter = painterResource(id = profile.profileImageRes),
                username = profile.username,
                level = profile.level,
                streakCount = profile.streakCount,
                daysActive = profile.daysActive,
                totalXp = profile.totalXp,
                colors = profile.colors
            )

            Divider(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Include the Achievement list in the profile screen
            AchievementList(
                viewModel = achievementViewModel,
                navController = navController
            )
        }
    }
}