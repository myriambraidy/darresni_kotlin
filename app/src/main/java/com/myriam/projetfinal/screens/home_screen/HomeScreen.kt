package com.myriam.projetfinal.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme // Keep MaterialTheme import
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myriam.projetfinal.components.ScreenHeader // Import ScreenHeader
import com.myriam.projetfinal.screens.exercises_screen.components.ExerciseCard
import com.myriam.projetfinal.screens.exercises_screen.sections.ExerciseDetails
import com.myriam.projetfinal.screens.home_screen.components.DailySection
import com.myriam.projetfinal.screens.home_screen.components.StreakSection

@Composable
fun HomeScreen(vm: HomeScreenViewModel, appNav: NavController) {

    val homeNavController = rememberNavController()

    NavHost(homeNavController, startDestination = "main") {
        composable("main") {
            HomeContent(vm, homeNavController, appNav)
        }

        composable("devpick_details") {
            // Ensure ExerciseDetails is adapted for dark mode internally
            vm.selectedDevPick?.let { exo ->
                ExerciseDetails(exo = exo, nav = homeNavController)
            }
        }
    }
}

@Composable
fun HomeContent(vm: HomeScreenViewModel, nav: NavController, appNav: NavController) {
    val darkBackground = Color(0xFF0505)
    val primaryTextColor = Color.White
    val devPickList by vm.filteredDevPick.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
            .padding(16.dp)
            .padding(horizontal = 4.dp)
    ) {

        ScreenHeader(title = "Welcome")
        Spacer(modifier = Modifier.height(32.dp))

        StreakSection()
        Spacer(modifier = Modifier.height(8.dp))

        DailySection(appNav = appNav)
        Spacer(modifier = Modifier.height(32.dp))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF363333),
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Devs’ pick of the day",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(devPickList) { devpick ->
                ExerciseCard(
                    title = devpick.title,
                    description = devpick.description,
                    id = devpick.id.toString(),
                    lang = devpick.lang,
                    difficulty = devpick.difficulty.toInt(),
                    onClick = {
                        vm.selectedDevPick = devpick
                        nav.navigate("devpick_details")
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
