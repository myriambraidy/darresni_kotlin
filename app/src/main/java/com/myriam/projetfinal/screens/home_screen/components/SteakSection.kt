package com.myriam.projetfinal.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StreakSection() {
    // Define colors for gradient border (similar to DailySection or different)
    val borderGradient = Brush.linearGradient(listOf(Color(0xFF8338EC), Color(0xFF3A86FF))) // Example: Purple to Blue
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2A2A40).copy(alpha = 0.4f), // Darker purple/blue tint start
            Color(0xFF1A1A2E).copy(alpha = 0.9f)  // Darker purple/blue tint end
        )
    )
    val cornerRadius = 16.dp

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(cornerRadius))
                .border(
                    width = 3.dp,
                    brush = borderGradient,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(0.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = 0.6f
                        shape = RoundedCornerShape(cornerRadius)
                        clip = true
                    }
                    .background(backgroundGradient) // Apply the gradient
                    .background(Color.White.copy(alpha = 0.15f)) // Frosted glass overlay
                    .blur(16.dp) // Apply blur to this background layer
                    // Add padding *here* to define the content area size
                    // The background effect will fill this padded area.
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Content Column (drawn on top of the background effect)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                // Apply padding again directly to the content column if the background
                // padding wasn't sufficient or if you removed the inner Box padding.
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    "Current Streak: 3", // Replace with actual data later
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White // Use light text color
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "🏆 Longest Streak: 5", // Replace with actual data later
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0) // Use secondary light text color
                )
            }
        }
    }
}
