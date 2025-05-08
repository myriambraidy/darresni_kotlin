package com.myriam.projetfinal.daily_challenge

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.data.network.dto.CorrectionData
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DailyChallengeViewModel(
    private val exercisesRepository: ExercisesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    // State for user answer
    var userAnswer by mutableStateOf("")

    // State for loading status
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // State for correction result
    private val _correctionResult = MutableStateFlow<CorrectionData?>(null)
    val correctionResult: StateFlow<CorrectionData?> = _correctionResult.asStateFlow()

    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Fallback exercise in case API call fails
    private val fallbackExercise = Exercise(
        title = "Find the Bug",
        exoType = "Code Review",
        description = "Identify and explain the issue in this code",
        content = "public int add(int a, int b) {\n" +
                "    // This function should add two numbers\n" +
                "    return a - b; // Bug: using subtraction instead of addition\n" +
                "}\n",
        id = 1,
        contentLink = "",
        lang = "java",
        difficulty = "1",
        hasAttempted = false
    )

    // Current exercise
    private var currentExercise: Exercise? = null

    init {
        loadTodayExercise()
    }

    private fun loadTodayExercise() {
        viewModelScope.launch {
            try {
                val token = userRepository.getAuthToken() ?: ""
                if (token.isNotEmpty()) {
                    val exercises = exercisesRepository.getExercisesFromApi(token)
                    if (exercises.isNotEmpty()) {
                        // For daily challenge, we'll use the first exercise
                        currentExercise = exercises.first()
                    } else {
                        currentExercise = fallbackExercise
                    }
                } else {
                    currentExercise = fallbackExercise
                }
            } catch (e: Exception) {
                Log.e("DailyChallengeVM", "Error loading exercises", e)
                currentExercise = fallbackExercise
            }
        }
    }

    fun getExercise(): Exercise {
        return currentExercise ?: fallbackExercise
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun submitAnswer() {
        val exercise = getExercise()

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val token = userRepository.getAuthToken() ?: ""
                if (token.isNotEmpty()) {
                    val result = exercisesRepository.correctExercise(
                        token = token,
                        exerciseId = exercise.id,
                        userAnswer = userAnswer
                    )

                    if (result != null) {
                        _correctionResult.value = result
                    } else {
                        _error.value = "Failed to get correction result"
                    }
                } else {
                    _error.value = "Authentication required"
                }
            } catch (e: HttpException) {
                _error.value = "Network error: ${e.message}"
                Log.e("DailyChallengeVM", "HTTP exception", e)
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                Log.e("DailyChallengeVM", "Error submitting answer", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearCorrectionResult() {
        _correctionResult.value = null
    }
}