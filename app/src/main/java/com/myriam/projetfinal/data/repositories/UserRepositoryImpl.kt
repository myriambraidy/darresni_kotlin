package com.myriam.projetfinal.data.repositories

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.ui.graphics.Color
import com.myriam.projetfinal.R // Make sure R is imported if using resources
import com.myriam.projetfinal.data.models.History
import com.myriam.projetfinal.data.models.User
import com.myriam.projetfinal.data.network.api.ApiService
import com.myriam.projetfinal.data.network.dto.LoginRequest
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private var authToken: String? = null

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun login(email: String, pass: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(email = email, password = pass)
                Log.d("UserRepository", "Attempting login for: $email")
                val response = apiService.login(loginRequest)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.status == "success" && loginResponse.message != null) {
                        val userDto = loginResponse.message.user
                        authToken = loginResponse.message.token // Store token (insecurely here!)
                        Log.d("BRAIDUX", "Login successful for $email. Token received. $authToken")

                        // --- Create User object using NEW UserDto ---
                        _currentUser.value = User(
                            // Map available data from UserDto
                            username = userDto.username, // Directly from DTO
                            email = userDto.email,       // Directly from DTO
                            streak = userDto.streak,
                            id = userDto.id.toString()
                        )
                        // --- ---

                        Log.i("UserRepository", "User object created: ${_currentUser.value?.username}")
                        true // Return true for success
                    } else {
                        val errorMessage = loginResponse?.data ?: loginResponse?.message?.toString() ?: "Invalid credentials or unknown API error"
                        Log.w("UserRepository", "API Login failed for $email: $errorMessage")
                        clearLocalUserData() // Clear local data on failure
                        false // Return false for failure
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown HTTP error"
                    Log.e("UserRepository", "API Login HTTP error - Code: ${response.code()}, Body: $errorBody")
                    clearLocalUserData()
                    false // Return false for failure
                }

            } catch (e: IOException) {
                Log.e("UserRepository", "API Login network error", e)
                clearLocalUserData()
                false
            } catch (e: HttpException) {
                Log.e("UserRepository", "API Login HTTP exception", e)
                clearLocalUserData()
                false
            } catch (e: Exception) {
                Log.e("UserRepository", "API Login unexpected error", e)
                clearLocalUserData()
                false
            }
        }
    }

    // Helper function to clear local state
    private fun clearLocalUserData() {
        _currentUser.value = null
        authToken = null
        Log.d("UserRepository", "Local user data and token cleared.")
    }

    override fun logout() {
        println("UserRepository: Logging out")
        _currentUser.value = null
        authToken = null // Clear the token
    }


    override fun getAuthToken(): String? {
        return authToken
    }
}