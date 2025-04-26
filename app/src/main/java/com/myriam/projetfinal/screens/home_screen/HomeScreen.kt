package com.myriam.projetfinal.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.components.ScreenHeader
import com.myriam.projetfinal.screens.exercises_screen.components.ExerciseCard
import com.myriam.projetfinal.screens.exercises_screen.sections.ExerciseDetails
import com.myriam.projetfinal.screens.home_screen.components.GraphSection
import com.myriam.projetfinal.screens.home_screen.components.StreakSection

@Composable
fun HomeScreen(vm: HomeScreenViewModel) {

    val homeNavController = rememberNavController()

    NavHost(homeNavController, startDestination = "home") {
        composable("home") {
            HomeContent(vm, homeNavController)
        }

        composable("devpick_details") {
            vm.selectedDevPick?.let { it1 -> ExerciseDetails(exo = it1, nav = homeNavController) }
        }
    }
}

@Composable
fun HomeContent(vm: HomeScreenViewModel, nav: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        ScreenHeader(title= "Welcome", count = 3)
        Spacer(modifier = Modifier.height(32.dp))

        StreakSection()
        Spacer(modifier = Modifier.height(32.dp))

        GraphSection()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Devs’ pick of the day",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(vm.devsPick) { devpick ->
                ExerciseCard(
                    painter = painterResource(id = devpick.imageRes),
                    title = devpick.title,
                    description = devpick.description,
                    id = devpick.id,
                    accentColor = devpick.accentColor,
                    onClick = {
                        vm.selectedDevPick = devpick
                        nav.navigate("devpick_details")

                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}