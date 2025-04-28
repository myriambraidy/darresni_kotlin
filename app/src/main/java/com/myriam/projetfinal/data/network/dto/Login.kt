package com.myriam.projetfinal.data.network.dto

import com.google.gson.annotations.SerializedName // Import if using Gson

// Request Body Structure
data class LoginRequest(
    val email: String,
    val password: String // Assuming password is sent in the body
)

// Response Body Structure (matching your example)
data class LoginResponse(
    @SerializedName("status") // Use if JSON key differs from variable name (optional with Gson if names match)
    val status: String,
    @SerializedName("message")
    val message: LoginMessage?, // Make nullable in case status is 'error' and message is missing/different
    @SerializedName("data")
    val data: String? // Or could be a more complex object depending on error responses
)

data class LoginMessage(
    @SerializedName("user")
    val user: UserDto,
    @SerializedName("token")
    val token: String
)

data class UserDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("isAdmin")
    val isAdmin: Int, // Or Boolean if the API returns true/false
    @SerializedName("streak")
    val streak: Int
)