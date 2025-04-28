package com.myriam.projetfinal

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Import ViewModels and Screens
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.navbar.MainScreen
import com.myriam.projetfinal.screens.daily_challenge.DailyChallengeScreen
import com.myriam.projetfinal.screens.login_screen.LoginScreen
import com.myriam.projetfinal.screens.login_screen.LoginScreenViewModel
import com.myriam.projetfinal.screens.signup_screen.SignupScreen
import com.myriam.projetfinal.screens.signup_screen.SignupScreenViewModel
// Import RootViewModel and state/routes
import com.myriam.projetfinal.viewmodels.InitialAuthState
import com.myriam.projetfinal.viewmodels.RootViewModel
import com.myriam.projetfinal.viewmodels.AppRoutes // Use the defined routes
// Koin integration
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    val rootViewModel: RootViewModel = koinViewModel()
    val initialAuthState by rootViewModel.initialAuthState.collectAsState()

    when (val state = initialAuthState) {
        is InitialAuthState.Loading -> {
            // Show loading indicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is InitialAuthState.Determined -> {
            Log.d("UserRepository", "Navigating to: ${state.startDestination}") // Log the destination

            // Setup NavHost with determined start destination
            NavHost(
                navController = appNavController,
                startDestination = state.startDestination // Dynamic start
            ) {
                // Authentication Flow
                composable(AppRoutes.LOGIN) {
                    val loginVM: LoginScreenViewModel = koinViewModel()
                    LoginScreen(
                        viewModel = loginVM,
                        navController = appNavController // Use main controller
                    )
                }
                composable(AppRoutes.SIGNUP) {
                    val signupVM: SignupScreenViewModel = koinViewModel()
                    SignupScreen(
                        viewModel = signupVM,
                        navController = appNavController // Use main controller
                    )
                }

                // Authenticated Flow
                composable(AppRoutes.MAIN) {
                    MainScreen(appNav = appNavController) // Pass main controller
                }

                // Other top-level destinations
                composable(AppRoutes.DAILY_CHALLENGE) {
                    val dailyVm: DailyChallengeViewModel = koinViewModel() // Inject via Koin
                    DailyChallengeScreen(
                        nav = appNavController,
                        vm = dailyVm
                    )
                }
            }
        }
    }
}