package com.myriam.projetfinal.screens.ProfileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myriam.projetfinal.Achievement.Achievement


@Composable
fun AchievementsSectionProfile(achievements: List<Achievement>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Achievements",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            achievements.forEach { achievement ->
                AchievementItem(achievement = achievement)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AchievementItem(achievement: Achievement) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Achievement icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    if (achievement.isUnlocked) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                ),
            contentAlignment = Alignment.Center
        ) {
            // In a real app, you would load this from a resource ID
            Text(
                text = achievement.title.first().toString(),
                style = MaterialTheme.typography.titleLarge,
                color = if (achievement.isUnlocked) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = achievement.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (achievement.isUnlocked) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            Text(
                text = achievement.description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (achievement.isUnlocked) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}