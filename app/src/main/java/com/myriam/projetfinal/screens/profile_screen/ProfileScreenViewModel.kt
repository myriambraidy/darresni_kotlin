package com.myriam.projetfinal.screens.ProfileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.flow.*
// Import the ProfileUiState from its new file
import com.myriam.projetfinal.screens.profile_screen.ProfileUiState

class ProfileScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // Expose the UI state derived from the repository's currentUser flow
    val uiState: StateFlow<ProfileUiState> = userRepository.currentUser
        .map { user ->
            // Map the User? object to the appropriate ProfileUiState
            if (user != null) {
                ProfileUiState.Success(user) // User data available
            } else {
                // No user data (either loading initially handled by initialValue,
                // or user logged out after being logged in)
                ProfileUiState.Error // Represent logged out / error state
            }
        }
        .stateIn( // Convert Flow to StateFlow for Compose UI consumption
            scope = viewModelScope,
            // Keep the flow active for 5 seconds after the last observer stops observing.
            // This helps survive configuration changes briefly.
            started = SharingStarted.WhileSubscribed(5000L),
            // The initial state while waiting for the first emission from the repository.
            initialValue = ProfileUiState.Loading
        )

    // Function to initiate the logout process via the repository
    fun performLogout() {
        userRepository.logout()
    }
}