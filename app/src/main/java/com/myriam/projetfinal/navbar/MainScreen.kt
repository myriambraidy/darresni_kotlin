package com.myriam.projetfinal.navbar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.screens.ProfileScreen.ProfileNav
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.exercises_screen.ExercisesNav
import com.myriam.projetfinal.screens.home_screen.HomeScreen
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(appNav: NavController) {
    val navController = rememberNavController()
    val homeVM: HomeScreenViewModel = koinViewModel()
    val exerciseVM: ExerciseViewModel = koinViewModel()
    val tabs = listOf(TabItem.Home, TabItem.Exercises, TabItem.Profile)

    Scaffold(
        bottomBar = { CustomBottomNav(navController, tabs) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TabItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.Home.route) { HomeScreen(vm = homeVM, appNav = appNav) }
            composable(TabItem.Exercises.route) { ExercisesNav(
                vm = exerciseVM
            )  }
            composable(TabItem.Profile.route) {
                val profileKey = remember { mutableStateOf(System.currentTimeMillis()) }

                // Reset the key every time you come back to Profile tab
                LaunchedEffect(key1 = navController.currentBackStackEntry?.destination?.route) {
                    if (navController.currentDestination?.route == TabItem.Profile.route) {
                        profileKey.value = System.currentTimeMillis()
                    }
                }

                key(profileKey.value) {
                    ProfileNav()
                }
            }
        }
    }
}
