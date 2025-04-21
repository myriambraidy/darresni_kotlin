package com.myriam.projetfinal.Achievement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myriam.projetfinal.R

@Composable
fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = achievement.colors,
                        startX = 0f,
                        endX = 300f
                    )
                )
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = achievement.imageRes),
                        contentDescription = achievement.title,
                        modifier = Modifier.fillMaxSize(),
                        alpha = if (achievement.isUnlocked) 1f else 0.5f
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp, top = 8.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = achievement.title,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp, start = 10.dp)
                    )
                    Text(
                        text = achievement.description,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = achievement.starsRes),
                            contentDescription = "Stars",
                            modifier = Modifier.size(100.dp)
                        )
                        Text(
                            text = achievement.progress,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                        if (achievement.isUnlocked) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "Unlocked",
                                tint = Color.Green,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
                Text(
                    text = achievement.id,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}