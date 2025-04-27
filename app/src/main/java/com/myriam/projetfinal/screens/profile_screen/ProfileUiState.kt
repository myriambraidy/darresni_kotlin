package com.myriam.projetfinal.screens.profile_screen


import com.myriam.projetfinal.data.models.User

sealed interface ProfileUiState {

    data object Loading : ProfileUiState

    data class Success(val user: User) : ProfileUiState

    data object Error :
        ProfileUiState
}