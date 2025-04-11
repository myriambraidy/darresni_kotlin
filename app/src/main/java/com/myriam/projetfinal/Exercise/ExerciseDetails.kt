package com.myriam.projetfinal.Exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myriam.projetfinal.ButtonVariant
import com.myriam.projetfinal.CustomButton
import com.myriam.projetfinal.navbar.TabItem.Exercises.icon

@Composable
fun ExerciseDetailScreen(vm: ExerciseViewModel,navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Details",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            vm.selectedExercise?.title?.let {
                Text(
                    text = it,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }




        }
        CustomButton(
            label = "X",
            variant = ButtonVariant.Destructive,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.TopStart),
            height = 40,
            width = 50,
            onClick = { navController.navigate("exercises") }



        ) {

        }
    }
}