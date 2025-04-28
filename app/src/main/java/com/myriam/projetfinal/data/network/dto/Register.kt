package com.myriam.projetfinal.data.network.dto

import com.google.gson.annotations.SerializedName // Import if using Gson

data class RegisterRequest(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class RegisterResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: RegisterResponseMessage?,
    @SerializedName("data") val data: String?
)

data class RegisterResponseMessage(
    @SerializedName("user") val user: UserDto?,
    @SerializedName("token") val token: String?
)