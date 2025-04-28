package com.myriam.projetfinal.viewmodels // Or your chosen package

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.repositories.UserRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Define possible initial states
sealed class InitialAuthState {
    object Loading : InitialAuthState()
    data class Determined(val startDestination: String) : InitialAuthState()
}

class RootViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _initialAuthState = MutableStateFlow<InitialAuthState>(InitialAuthState.Loading)
    val initialAuthState: StateFlow<InitialAuthState> = _initialAuthState.asStateFlow()

    init {
        // Observe the user state from the repository
        viewModelScope.launch {
            // Simulate a short delay for loading state visibility
            kotlinx.coroutines.delay(100)
            userRepository.currentUser
                .map { user ->
                    Log.d("UserRepository", "currentUser emitted: $user (initial check)")
                    if (user != null) {
                        InitialAuthState.Determined(AppRoutes.MAIN)
                    } else {
                        InitialAuthState.Determined(AppRoutes.LOGIN)
                    }
                }
                .collect { state ->
                    Log.d("UserRepository", "initialAuthState collected (initial): $state")
                    _initialAuthState.value = state
                }
        }
    }
}

// Define routes centrally (can be in a separate file)
object AppRoutes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val MAIN = "main"
    const val DAILY_CHALLENGE = "daily_question"
}