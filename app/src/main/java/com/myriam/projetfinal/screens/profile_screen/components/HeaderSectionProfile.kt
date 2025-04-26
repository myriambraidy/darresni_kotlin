package com.myriam.projetfinal.screens.ProfileScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myriam.projetfinal.R
import com.myriam.projetfinal.UserProfile.UserProfile
import com.myriam.projetfinal.UserProfile.UserProfileCard

@Composable
fun HeaderSectionProfile(
    userProfile: UserProfile,
    onSettingsClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // User Profile Card
        UserProfileCard(
            profilePainter = painterResource(id = R.drawable.ic_launcher_foreground),
            username = userProfile.username,
            level = userProfile.level,
            streakCount = userProfile.streakCount,
            daysActive = userProfile.daysActive,
            totalXp = userProfile.totalXp,
            colors = listOf(
                Color(0xFFE0F7FA),
                Color(0xFFB2EBF2)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Settings button positioned on top of the card
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )
        }
    }
}