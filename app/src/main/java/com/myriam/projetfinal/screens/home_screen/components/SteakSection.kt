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
    val borderGradient = Brush.linearGradient(listOf(Color(0xFF9C27B0), Color(0xFF00BCD4)))
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2A2A40).copy(alpha = 0.4f),
            Color(0xFF1A1A2E).copy(alpha = 0.5f)
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
                    .background(backgroundGradient)
                    .background(Color.White.copy(alpha = 0.15f))
                    .blur(16.dp)

                    .padding(horizontal = 24.dp, vertical = 16.dp)
            )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    "Current Streak: 3",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "🏆 Longest Streak: 5",
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0)
                )
            }
        }
    }
}
