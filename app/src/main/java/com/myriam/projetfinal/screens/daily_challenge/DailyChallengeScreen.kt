package com.myriam.projetfinal.screens.daily_challenge

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myriam.projetfinal.components.ButtonVariant
import com.myriam.projetfinal.components.CustomButton
import com.myriam.projetfinal.components.CustomTextField
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.daily_challenge.ResultPopup
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DailyChallengeScreen(
    nav: NavController,
    vm: DailyChallengeViewModel
) {
    var isAnswerView by remember { mutableStateOf(false) }
    val showPopup = remember { mutableStateOf(false) }

    // Collect states from ViewModel
    val isLoading by vm.isLoading.collectAsState()
    val correctionResult by vm.correctionResult.collectAsState()
    val error by vm.error.collectAsState()

    val exercise = vm.getExercise()

    fun getCurrentFormattedDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)
        return today.format(formatter)
    }

    // Effect to show popup when correction result is available
    LaunchedEffect(correctionResult) {
        if (correctionResult != null) {
            showPopup.value = true
        }
    }

    // Error handling effect
    LaunchedEffect(error) {
        if (error != null) {
            // You could show a toast or snackbar here
            // For now, we'll just log it
            println("Error: $error")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color(0xFF124A49),
                            0.3f to Color(0xFF022D2C),
                            0.5f to Color(0xFF1A1A1A),
                            1.0f to Color(0xFF1A1A1A)
                        )
                    )
                )
                .blur(30.dp),
        )

        // Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = 24.dp)
        ) {
            IconButton(
                onClick = {
                    nav.popBackStack()
                    vm.userAnswer = ""
                },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
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
                Text(
                    text = getCurrentFormattedDate(),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF705D56))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "daily challenge",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (!isAnswerView) {
                        Text(
                            text = exercise.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = exercise.exoType,
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF1E1E1E))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = exercise.content,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                color = Color.White,
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
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Provide your solution and explanation clearly below.",
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        CustomTextField(
                            value = vm.userAnswer,
                            onValueChange = { vm.userAnswer = it },
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

                        if (isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            CustomButton(
                                label = "Submit",
                                onClick = { vm.submitAnswer() },
                                width = 250,
                                height = 45,
                                variant = ButtonVariant.Default
                            )
                        }
                    }
                }
            }
        }
    }

    if (showPopup.value && correctionResult != null) {
        ResultPopup(
            score = "${correctionResult!!.score}%",
            explanation = correctionResult!!.feedback,
            onDismiss = {
                showPopup.value = false
                vm.clearCorrectionResult()
                nav.navigate("main")
            }
        )
    }
}