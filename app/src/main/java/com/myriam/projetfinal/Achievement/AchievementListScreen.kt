package com.myriam.projetfinal.Achievement

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AchievementList(
    viewModel: AchievementViewModel = viewModel(),
    navController: NavController
) {
    val achievements by viewModel.achievements.observeAsState(emptyList())

    LazyColumn {
        items(achievements) { achievement ->
            AchievementCard(
                achievement = achievement,
                onClick = {
                    navController.navigate("achievement_details")
                    viewModel.selectedAchievement = achievement
                }
            )
        }
    }
}