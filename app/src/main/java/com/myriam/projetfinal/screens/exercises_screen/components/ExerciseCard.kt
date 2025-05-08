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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myriam.projetfinal.R

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    id: String,
    difficulty: Int = 5,
    lang: String,
    onClick: () -> Unit
) {
    // get accent color and painter based on lang
    val accentColor = getLanguageColor(lang)
    val painter = getLanguageLogoPainter(lang)

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
                            text = title ,
                            color = textColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row {
                            for (i in 0..<difficulty) {
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


                Text(
                    text = description  ,
                    color = textColor.copy(alpha = 0.95f),
                    fontSize = 14.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }

        }
    }
}

@Composable
fun getLanguageColor(lang: String): Color {
    return when (lang.lowercase()) {
        "python" -> Color(0xFF3A83BD) // Python Blue
        "cpp", "c++" -> Color(0xFF002846) // C++ Blue
        "javascript", "js" -> Color(0xFFF7DF1E) // JavaScript Yellow
        "java" -> Color(0xFFB07219) // Java Brown
        "kotlin" -> Color(0xFFAA77FF) // Kotlin Purple
        "swift" -> Color(0xFFF05138) // Swift Orange-Red
        "c#" -> Color(0xFF178600) // C# Green
        "php" -> Color(0xFF777BB4) // PHP Purple-Blue
        "ruby" -> Color(0xFFCC342D) // Ruby Red
        "go", "golang" -> Color(0xFF00ADD8) // Go Cyan
        "typescript", "ts" -> Color(0xFF2B7489) // TypeScript Teal
        "rust" -> Color(0xFFDE28FF) // Rust Purple
        "scala" -> Color(0xFFc22d40) // Scala Red
        "html" -> Color(0xFFE34F26) // HTML Orange
        "css" -> Color(0xFF563D7C) // CSS Purple
        "dart" -> Color(0xFF00B4AB) // Dart Teal
        "objective-c" -> Color(0xFF438EFF) // Objective-C Blue
        "r" -> Color(0xFF198CE7) // R Blue
        "swiftui" -> Color(0xFFF05138) // SwiftUI Orange-Red (same as Swift for now)
        "compose" -> Color(0xFF4285F4) // Compose Blue (Material Design)
        "sql" -> Color(0xFF439894) // SQL Teal
        else -> Color.Gray
    }
}

// Helper function to get logo painter based on language
@Composable
fun getLanguageLogoPainter(lang: String): Painter {
    val logoResId = when (lang.lowercase()) {
        "python" -> R.drawable.python_logo // Use your Python logo
        "cpp", "c++" -> R.drawable.cpplogo   // Use your C++ logo
        "javascript", "js" -> R.drawable.javascript_logo
        "css" -> R.drawable.css_logo
        "kotlin" -> R.drawable.kotlin_logo
        else -> R.drawable.default_logo // Default icon
    }
    return painterResource(id = logoResId)
}