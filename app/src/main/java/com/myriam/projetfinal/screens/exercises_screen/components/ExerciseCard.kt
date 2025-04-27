package com.myriam.projetfinal.screens.exercises_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    description: String,
    id: String,
    difficulty: Int = 5,
    accentColor: Color = Color(0xFB13CC02),
    onClick: () -> Unit
) {
    val cardGradient = listOf(
        accentColor.copy(alpha = 0.8f),
        accentColor.copy(alpha = 0.35f)
    )

    val textColor = Color.White
    val starColor = Color(0xFFFFD700)
    val glassBackgroundColor = Color.White.copy(alpha = 0.15f)
    val glassBorderColor = Color.White.copy(alpha = 0.25f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = cardGradient,
                        start = Offset(0f, 0f),
                        end = Offset(600f, 400f)
                    )
                )
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top Row: Icon + Title + Stars + Play
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(glassBackgroundColor, CircleShape)
                            .border(1.dp, glassBorderColor, CircleShape)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = title,
                            modifier = Modifier.fillMaxSize(),
                            colorFilter = ColorFilter.tint(textColor)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row {
                            for (i in 1..5) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = if (i <= difficulty) starColor else textColor.copy(alpha = 0.3f),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Description at the bottom
                Text(
                    text = description,
                    color = textColor.copy(alpha = 0.95f),
                    fontSize = 14.sp,
                    maxLines = 3, // ✂ Limit to 3 lines
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }

            // ID Badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .background(glassBackgroundColor, CircleShape)
                    .border(1.dp, glassBorderColor, CircleShape)
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = id,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}