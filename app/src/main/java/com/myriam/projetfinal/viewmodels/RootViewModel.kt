package com.myriam.projetfinal.viewmodels // Or your chosen package

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.repositories.UserRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Define possible initial states
sealed class InitialAuthState {
    object Loading : InitialAuthState()
    data class Determined(val startDestination: String) : InitialAuthState()
}

class RootViewModel(
    private val userRepository: UserRepository,
    private val exercisesRepository: ExercisesRepository // Inject ExercisesRepository
) : ViewModel() {

    private val _initialAuthState = MutableStateFlow<InitialAuthState>(InitialAuthState.Loading)
    val initialAuthState: StateFlow<InitialAuthState> = _initialAuthState.asStateFlow()

    init {
        determineInitialAuthState()
        loadInitialData() // Call the new function here
    }

    private fun determineInitialAuthState() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(100)
            userRepository.currentUser
                .map { user ->
                    Log.d("RootViewModel", "currentUser emitted: $user (initial check)")
                    if (user != null) {
                        InitialAuthState.Determined(AppRoutes.MAIN)
                    } else {
                        InitialAuthState.Determined(AppRoutes.LOGIN)
                    }
                }
                .collect { state ->
                    Log.d("RootViewModel", "initialAuthState collected (initial): $state")
                    _initialAuthState.value = state
                }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            var token: String? = null
            var retryCount = 0
            val maxRetries = 50 // You can adjust the maximum number of retries
            val delayMillis = 1200L

            while (token == null && retryCount < maxRetries) {
                try {
                    token = userRepository.getAuthToken()
                    if (token != null) {
                        Log.d("BRAIDUX", "HOOO $token")
                        val exercises = exercisesRepository.getExercisesFromApi(token)
                        Log.d("BRAIDUX", "Loaded ${exercises.size} exercises")
                        // Assuming you have a StateFlow in your ViewModel to hold these exercises
                        // _exercises.value = exercises
                        break // Exit the loop if the token is successfully retrieved and data is fetched
                    } else {
                        Log.w("BRAIDUX", "Auth token is null, retrying in ${delayMillis / 1000} seconds (attempt ${retryCount + 1})")
                        retryCount++
                        delay(delayMillis)
                    }
                } catch (e: Exception) {
                    Log.e("BRAIDUX", "Error loading initial data (attempt ${retryCount + 1}): ${e.message}", e)
                    retryCount++
                    if (retryCount < maxRetries) {
                        delay(delayMillis)
                    } else {
                        // Handle the case where all retries failed
                        Log.e("BRAIDUX", "Failed to load initial data after $maxRetries retries")
                        // You might want to update a state in your ViewModel to indicate an error
                    }
                }
            }

            if (token == null) {
                Log.w("BRAIDUX", "Failed to retrieve auth token after $maxRetries retries.")
                // Handle the case where the token could not be retrieved after all retries
                // You might want to update a state in your ViewModel to indicate an error
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