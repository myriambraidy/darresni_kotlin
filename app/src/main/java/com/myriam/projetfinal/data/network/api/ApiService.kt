package com.myriam.projetfinal.data.network.api

import com.myriam.projetfinal.data.network.dto.ExerciseResponse
import com.myriam.projetfinal.data.network.dto.LoginRequest
import com.myriam.projetfinal.data.network.dto.LoginResponse
import retrofit2.Response // Import Retrofit's Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    // Assuming your login endpoint is at "api/auth/login" relative to your base URL
    @POST("users/login") // Replace with your actual endpoint path
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    // Using Response<T> gives access to HTTP status codes, headers, etc.
    // If you only care about the body and want Retrofit to throw exceptions for non-2xx codes,
    // you can return LoginResponse directly:
    // suspend fun login(@Body request: LoginRequest): LoginResponse
    @GET("exercises")
    suspend fun getExercises(@Header("Authorization") token: String): Response<ExerciseResponse>
}