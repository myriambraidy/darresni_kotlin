package com.myriam.projetfinal.Settings

data class SettingsUiState(
    val dailyGoal: Int = 15,
    val soundEnabled: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val codeLanguages: List<String> = emptyList(),
    val selectedLanguageIndex: Int = 0,
    val difficultyLevel: DifficultyLevel = DifficultyLevel.BEGINNER
)

enum class DifficultyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
}