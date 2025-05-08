package com.myriam.projetfinal.data.network.api

import com.myriam.projetfinal.data.network.dto.CorrectionRequest
import com.myriam.projetfinal.data.network.dto.CorrectionResponse
import com.myriam.projetfinal.data.network.dto.ExerciseResponse
import com.myriam.projetfinal.data.network.dto.LoginRequest
import com.myriam.projetfinal.data.network.dto.LoginResponse
import com.myriam.projetfinal.data.network.dto.RegisterRequest
import com.myriam.projetfinal.data.network.dto.RegisterResponse
import retrofit2.Response // Import Retrofit's Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Registration Endpoint
    @POST("users/signup") // Replace with your actual registration endpoint path if different
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>


    @GET("exercises")
    suspend fun getExercises(@Header("Authorization") token: String): Response<ExerciseResponse>

    @POST("exercises/correct")
    suspend fun correctExercise(
        @Body correctionRequest: CorrectionRequest,
        @Header("Authorization") token: String
    ): Response<CorrectionResponse>
}