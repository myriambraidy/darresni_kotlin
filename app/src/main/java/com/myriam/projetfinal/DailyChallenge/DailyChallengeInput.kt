package com.myriam.projetfinal.DailyChallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myriam.projetfinal.ButtonVariant
import com.myriam.projetfinal.CustomButton
import com.myriam.projetfinal.CustomTextField
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DailyChallengeWriteScreen(
    nav : NavController,
    code: String,
    onCodeChange: (String) -> Unit,

) {
    val showPopup = remember { mutableStateOf(false) }

    fun getCurrentFormattedDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)
        return today.format(formatter)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    nav.popBackStack("all_exercises", inclusive = false) // 🔄 Adjust "main" to your actual route
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.DarkGray
                    )
                }
                Text(
                    text = getCurrentFormattedDate(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(5f)                )

            }

            // Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFB84D33))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "daily challenge",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Title
            Text(
                text = "Debug and Fix",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            // Custom Text Field (code input)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFEFEF))
                    .padding(top = 4.dp)
            ) {
                CustomTextField(
                    label = "Your Answer",
                    value = code,
                    onValueChange = onCodeChange,
                    placeholder = "Write your code or explanation here...",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }

            // Buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                CustomButton(
                    label = "Back to Text",
                    onClick = {nav.popBackStack()},
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

    if (showPopup.value) {
        ResultPopup(
            score = "80%", // you can replace this with dynamic logic
            explanation = "Good job! You’ve fixed most of the bugs, but some edge cases are still failing." +
                    "Good job! You’ve fixed most of the bugs, but some edge cases are still failing.",
            onDismiss = {
                showPopup.value = false
            }
        )
    }
}
