package com.myriam.projetfinal.data.repositories

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.myriam.projetfinal.data.models.User
import com.myriam.projetfinal.data.network.api.ApiService
import com.myriam.projetfinal.data.network.dto.LoginRequest
import com.myriam.projetfinal.data.network.dto.RegisterRequest
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.IOException

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
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
                        Log.d("UserRepository", "Login successful for $email. Token received. $authToken")

                        _currentUser.value = User(
                            username = userDto.username,
                            email = userDto.email,
                            streak = userDto.streak,
                            id = userDto.id.toString()
                        )
                        Log.i("UserRepository", "User object created: ${_currentUser.value?.username}")
                        true
                    } else {
                        val errorMessage = loginResponse?.data ?: loginResponse?.message?.toString()
                        Log.w("UserRepository", "API Login failed for $email: $errorMessage")
                        clearLocalUserData()
                        false
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown HTTP error"
                    Log.e("UserRepository", "API Login HTTP error - Code: ${response.code()}, Body: $errorBody")
                    clearLocalUserData()
                    false
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

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun register(username: String, email: String, pass: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val registerRequest = RegisterRequest(username = username, email = email, password = pass)
                Log.d("UserRepository", "Attempting registration for: $email")
                val response = apiService.register(registerRequest)
                Log.d("UserRepository", "Attempting response: $response")


                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null && registerResponse.status == "success" && registerResponse.message != null) {
                        val userDto = registerResponse.message.user
                        authToken = registerResponse.message.token // Store token after registration
                        Log.d("UserRepository", "Registration successful for $email. Token received: $authToken")

                        if (userDto != null) {
                            _currentUser.value = User(
                                username = userDto.username,
                                email = userDto.email,
                                streak = userDto.streak,
                                id = userDto.id.toString()
                            )
                        }
                        Log.i("UserRepository", "Registered user object created: ${_currentUser.value?.username}")
                        true
                    } else {
                        val errorMessage = registerResponse?.data ?: registerResponse?.message?.toString() ?: "Registration failed: Invalid data or server error"
                        Log.w("UserRepository", "API Registration failed for $email: $errorMessage")
                        false
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown HTTP error during registration"
                    Log.e("UserRepository", "API Registration HTTP error - Code: ${response.code()}, Body: $errorBody")
                    false
                }

            } catch (e: IOException) {
                Log.e("UserRepository", "API Registration network error", e)
                false
            } catch (e: HttpException) {
                Log.e("UserRepository", "API Registration HTTP exception", e)
                false
            } catch (e: Exception) {
                Log.e("UserRepository", "API Registration unexpected error", e)
                false
            }
        }
    }

    private fun clearLocalUserData() {
        _currentUser.value = null
        authToken = null
        Log.d("UserRepository", "Local user data and token cleared.")
    }

    override fun logout() {
        println("UserRepository: Logging out")
        _currentUser.value = null
        authToken = null
    }

    override fun getAuthToken(): String? {
        return authToken
    }
}