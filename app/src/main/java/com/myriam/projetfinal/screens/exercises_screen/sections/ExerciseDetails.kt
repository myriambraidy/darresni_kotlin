package com.myriam.projetfinal.screens.exercises_screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ButtonVariant
import com.myriam.projetfinal.components.CustomButton
import com.myriam.projetfinal.components.CustomTextField
import com.myriam.projetfinal.data.models.Exercise


@Composable
fun ExerciseDetails(
    exo: Exercise, nav: NavController
) {
    var isAnswerView by remember { mutableStateOf(false) }
    var answerText by remember { mutableStateOf("") }
    val showPopup = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .padding(top = 24.dp)
    ) {
        IconButton(
            onClick = {
                nav.popBackStack();
                answerText = ""
            },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!isAnswerView) {
                    Text(
                        text = exo.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = exo.category,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = exo.question,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }

                    CustomButton(
                        label = "Start Writing",
                        onClick = { isAnswerView = true },
                        width = 250,
                        height = 45,
                        variant = ButtonVariant.Default
                    )
                } else {
                    Text(
                        text = "Write Your Answer",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Provide your solution and explanation clearly below.",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    CustomTextField(
                        value = answerText,
                        onValueChange = { answerText = it },
                        placeholder = "Write your code...",
                        label = ""
                    )

                    CustomButton(
                        label = "Back to Question",
                        onClick = {
                            isAnswerView = false
                        },
                        width = 250,
                        height = 45,
                        variant = ButtonVariant.Outline
                    )

                    CustomButton(
                        label = "Submit",
                        onClick = { showPopup.value = true },
                        width = 250,
                        height = 45,
                        variant = ButtonVariant.Default
                    )
                }
            }
        }
    }
    if (showPopup.value) {
        com.myriam.projetfinal.daily_challenge.ResultPopup(
            score = "80%", // you can replace this with dynamic logic
            explanation = "Good job! You've fixed most of the bugs, but some edge cases are still failing." +
                    "Good job! You've fixed most of the bugs, but some edge cases are still failing.",
            onDismiss = {
                showPopup.value = false
                nav.navigate("main")
            }
        )
    }
}