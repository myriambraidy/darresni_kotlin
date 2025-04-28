package com.myriam.projetfinal.data.repositories.interfaces

import com.myriam.projetfinal.data.models.User
import kotlinx.coroutines.flow.StateFlow // Import StateFlow

interface UserRepository {
    val currentUser: StateFlow<User?>

    suspend fun login(email: String, pass: String): Boolean

    fun logout()
}