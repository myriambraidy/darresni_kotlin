package com.myriam.projetfinal.Settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    // Settings state
    private val _settingsUiState = MutableStateFlow(SettingsUiState())
    val settingsUiState: StateFlow<SettingsUiState> = _settingsUiState.asStateFlow()

    // User preferences repository would be injected in a real app
    // private val userPreferencesRepository: UserPreferencesRepository

    init {
        // In a real app, we would load saved preferences here
        loadSettings()
    }

    private fun loadSettings() {
        // Simulating loading settings from a repository
        viewModelScope.launch {
            // In real implementation, you would get these from the repository
            _settingsUiState.value = SettingsUiState(
                dailyGoal = 20,
                soundEnabled = true,
                notificationsEnabled = true,
                darkModeEnabled = false,
                codeLanguages = listOf("Kotlin", "Java", "Python", "JavaScript"),
                selectedLanguageIndex = 0,
                difficultyLevel = DifficultyLevel.INTERMEDIATE
            )
        }
    }

    fun updateDailyGoal(minutes: Int) {
        _settingsUiState.value = _settingsUiState.value.copy(dailyGoal = minutes)
        saveSettings()
    }

    fun toggleSound(enabled: Boolean) {
        _settingsUiState.value = _settingsUiState.value.copy(soundEnabled = enabled)
        saveSettings()
    }

    fun toggleNotifications(enabled: Boolean) {
        _settingsUiState.value = _settingsUiState.value.copy(notificationsEnabled = enabled)
        saveSettings()
    }

    fun toggleDarkMode(enabled: Boolean) {
        _settingsUiState.value = _settingsUiState.value.copy(darkModeEnabled = enabled)
        saveSettings()
    }

    fun updatePrimaryLanguage(index: Int) {
        _settingsUiState.value = _settingsUiState.value.copy(selectedLanguageIndex = index)
        saveSettings()
    }

    fun updateDifficultyLevel(level: DifficultyLevel) {
        _settingsUiState.value = _settingsUiState.value.copy(difficultyLevel = level)
        saveSettings()
    }

    private fun saveSettings() {
        // In real implementation, save settings to repository
        viewModelScope.launch {
            // userPreferencesRepository.saveSettings(_settingsUiState.value)
        }
    }

    fun signOut() {
        // Handle sign out logic
        // In real implementation, clear user session, navigate to login, etc.
    }

    fun resetProgress() {
        // Handle resetting user progress
        // In real implementation, clear progress data from database
    }
}
