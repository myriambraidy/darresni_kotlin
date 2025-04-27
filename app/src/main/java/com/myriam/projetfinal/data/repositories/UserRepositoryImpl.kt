package com.myriam.projetfinal.data.repositories

import androidx.compose.ui.graphics.Color
import com.myriam.projetfinal.R // Make sure R is imported if using resources
import com.myriam.projetfinal.data.models.History
import com.myriam.projetfinal.data.models.User
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay

class UserRepositoryImpl: UserRepository {

    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    override suspend fun login(email: String, pass: String): Boolean {
        delay(500)
        val loggedIn = email == "test@example.com" && pass == "password123"

        if (loggedIn) {
            // --- Create User object WITH sample history ---
            _currentUser.value = User(
                username = "Test User",
                email = email,
                level = 5, // Sample data
                daysActive = 30,
                totalXp = 1250,
                profileImageRes = R.drawable.cpplogo, // Example drawable
                colors = listOf(Color(0xFF3F51B5), Color(0xFF7986CB)), // Example colors
                history = listOf( // Sample history data
                    History("h1", "Completed 'Variables'", "2025-04-26", true),
                    History("h2", "Attempted 'Loops'", "2025-04-25", false),
                    History("h3", "Completed 'Hello World'", "2025-04-24", true)
                )
            )
            // --- ---
            println("UserRepository: Login successful for $email, user data updated with history.")
            return true
        } else {
            println("UserRepository: Login failed for $email")
            _currentUser.value = null
            return false
        }
    }

    override fun logout() {
        println("UserRepository: Logging out")
        _currentUser.value = null
    }
}