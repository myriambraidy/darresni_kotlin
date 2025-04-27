package com.myriam.projetfinal.di

// --- Ensure ALL necessary imports are present ---
import com.myriam.projetfinal.data.repositories.ExercisesRepositoryImpl
import com.myriam.projetfinal.data.repositories.UserRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import com.myriam.projetfinal.screens.login_screen.LoginScreenViewModel
import com.myriam.projetfinal.screens.signup_screen.SignupScreenViewModel
import com.myriam.projetfinal.viewmodels.RootViewModel // Check path
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel // Check path
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreenViewModel
// *** IMPORT THE PROFILE VIEWMODEL ***
 // <-- Make sure this import is correct

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositories
    single<UserRepository> { UserRepositoryImpl() }
    single { ExercisesRepositoryImpl() }

    // ViewModels
    viewModel { RootViewModel(get()) }
    viewModel { ExerciseViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get()) }
    viewModel { SignupScreenViewModel(get()) }
    viewModel { DailyChallengeViewModel(/* dependencies */) }
    viewModel { ProfileScreenViewModel(get()) }

}