package com.myriam.projetfinal.di

import com.myriam.projetfinal.data.repositories.ExercisesRepositoryImpl
import com.myriam.projetfinal.data.repositories.UserRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }

    single<ExercisesRepositoryImpl> { ExercisesRepositoryImpl() }
    viewModel { ExerciseViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
}