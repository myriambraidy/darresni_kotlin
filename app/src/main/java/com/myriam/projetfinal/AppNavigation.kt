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
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel
import com.myriam.projetfinal.navbar.MainScreen
import com.myriam.projetfinal.screens.daily_challenge.DailyChallengeScreen
import com.myriam.projetfinal.screens.login_screen.LoginScreen
import com.myriam.projetfinal.screens.login_screen.LoginScreenViewModel
import com.myriam.projetfinal.screens.signup_screen.SignupScreen
import com.myriam.projetfinal.screens.signup_screen.SignupScreenViewModel
import com.myriam.projetfinal.viewmodels.InitialAuthState
import com.myriam.projetfinal.viewmodels.RootViewModel
import com.myriam.projetfinal.viewmodels.AppRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    val rootViewModel: RootViewModel = koinViewModel()
    val initialAuthState by rootViewModel.initialAuthState.collectAsState()

    when (val state = initialAuthState) {
        is InitialAuthState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is InitialAuthState.Determined -> {
            Log.d("UserRepository", "Navigating to: ${state.startDestination}") // Log the destination


            NavHost(
                navController = appNavController,
                startDestination = state.startDestination
            ) {
                composable(AppRoutes.LOGIN) {
                    val loginVM: LoginScreenViewModel = koinViewModel()
                    LoginScreen(
                        viewModel = loginVM,
                        navController = appNavController
                    )
                }
                composable(AppRoutes.SIGNUP) {
                    val signupVM: SignupScreenViewModel = koinViewModel()
                    SignupScreen(
                        viewModel = signupVM,
                        navController = appNavController
                    )
                }

                composable(AppRoutes.MAIN) {
                    MainScreen(appNav = appNavController)
                }

                composable(AppRoutes.DAILY_CHALLENGE) {
                    val dailyVm: DailyChallengeViewModel = koinViewModel()
                    DailyChallengeScreen(
                        nav = appNavController,
                        vm = dailyVm
                    )
                }
            }
        }
    }
}