package com.myriam.projetfinal.viewmodels // Or your chosen package

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            // Add a small delay to prevent flickering if auth state loads instantly
            kotlinx.coroutines.delay(100)
            userRepository.currentUser
                // .distinctUntilChanged() // Only react to actual changes
                .map { user ->
                    val destination = if (user != null) AppRoutes.MAIN else AppRoutes.LOGIN
                    InitialAuthState.Determined(destination)
                }
                .collect { state ->
                    _initialAuthState.value = state
                    println("RootViewModel: Auth state changed, initial route: ${(state as? InitialAuthState.Determined)?.startDestination}")
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