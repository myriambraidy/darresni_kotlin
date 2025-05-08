package com.myriam.projetfinal.data.network.dto

import com.google.gson.annotations.SerializedName // Import if using Gson

data class CorrectionRequest(
    @SerializedName("exerciseId") val exerciseId: Int,
    @SerializedName("userAnswer") val userAnswer: String
)

data class CorrectionResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CorrectionData
)

data class CorrectionData(
    @SerializedName("score") val score: Int,
    @SerializedName("feedback") val feedback: String,
    @SerializedName("logId") val logId: Int
)