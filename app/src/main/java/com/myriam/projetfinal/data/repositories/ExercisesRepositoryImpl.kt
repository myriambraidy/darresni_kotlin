package com.myriam.projetfinal.data.repositories

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.data.network.api.ApiService
import com.myriam.projetfinal.data.network.dto.CorrectionData
import com.myriam.projetfinal.data.network.dto.CorrectionRequest
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.IOException

class ExercisesRepositoryImpl(
    private val apiService: ApiService
) : ExercisesRepository {
    // Use MutableStateFlow to hold the list of exercises
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())

    // Expose it as a StateFlow for read-only access from outside
    override val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    //Use a backing field for devPick
    private val _devPick = MutableStateFlow<List<Exercise>>(emptyList())
    override val devPick: StateFlow<List<Exercise>> = _devPick.asStateFlow()


    override fun getExercises(): List<Exercise> {
        return _exercises.value // returns the current value of the StateFlow
    }

    override fun getDevPick(): List<Exercise> {
        return devPick.value
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getExercisesFromApi(token: String): List<Exercise> =
        withContext(Dispatchers.IO) {
            try {
                val tokenWithBearer = "Bearer $token"
                val response = apiService.getExercises(tokenWithBearer)
                if (response.isSuccessful) {
                    val exerciseResponse = response.body()
                    if (exerciseResponse != null && exerciseResponse.status == "success") {
                        Log.d(
                            "ExercisesRepositoryImpl",
                            "Successfully fetched exercises from API"
                        )
                        val mappedExercises = exerciseResponse.data.map { exerciseDto ->
                            Exercise(
                                id = exerciseDto.id,
                                title = exerciseDto.title,
                                description = exerciseDto.description,
                                content = exerciseDto.content,
                                contentLink = exerciseDto.content_link
                                    ?: "", // Provide a default empty string
                                difficulty = exerciseDto.difficulty,
                                exoType = exerciseDto.exo_type,
                                lang = exerciseDto.lang,
                                hasAttempted = exerciseDto.hasAttempted
                            )
                        }
                        Log.d("BRAIDUX", "My Data $mappedExercises")
                        _exercises.value =
                            mappedExercises // Update the StateFlow's value, triggering recomposition
                        _devPick.value = if (mappedExercises.count() > 2) mappedExercises.subList(
                            0,
                            2
                        ) else emptyList()
                        return@withContext mappedExercises
                    } else {
                        val errorMessage =
                            exerciseResponse?.message ?: "Failed to fetch exercises"
                        Log.e(
                            "ExercisesRepositoryImpl",
                            "Failed to fetch exercises from API: $errorMessage"
                        )
                        return@withContext emptyList()
                    }
                } else {
                    val errorBody =
                        response.errorBody()?.string() ?: "Unknown HTTP error"
                    Log.e(
                        "ExercisesRepositoryImpl",
                        "Error fetching exercises from API. Code: ${response.code()}, Body: $errorBody"
                    )
                    return@withContext emptyList()
                }
            } catch (e: IOException) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "Network error fetching exercises from API",
                    e
                )
                return@withContext emptyList()
            } catch (e: HttpException) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "HTTP exception fetching exercises from API",
                    e
                )
                return@withContext emptyList()
            } catch (e: Exception) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "Unexpected error fetching exercises from API",
                    e
                )
                return@withContext emptyList()
            }
        }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun correctExercise(token: String, exerciseId: Int, userAnswer: String): CorrectionData? =
        withContext(Dispatchers.IO) {
            try {
                val tokenWithBearer = "Bearer $token"
                val correctionRequest = CorrectionRequest(
                    exerciseId = exerciseId,
                    userAnswer = userAnswer
                )

                Log.d("ExercisesRepositoryImpl", "Attempting to correct exercise with ID: $exerciseId")
                val response = apiService.correctExercise(correctionRequest, tokenWithBearer)

                if (response.isSuccessful) {
                    val correctionResponse = response.body()
                    if (correctionResponse != null && correctionResponse.status == "success") {
                        Log.d(
                            "ExercisesRepositoryImpl",
                            "Successfully corrected exercise: Score ${correctionResponse.data.score}"
                        )
                        return@withContext correctionResponse.data
                    } else {
                        val errorMessage = correctionResponse?.message ?: "Failed to correct exercise"
                        Log.e(
                            "ExercisesRepositoryImpl",
                            "Failed to correct exercise: $errorMessage"
                        )
                        return@withContext null
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown HTTP error"
                    Log.e(
                        "ExercisesRepositoryImpl",
                        "Error correcting exercise. Code: ${response.code()}, Body: $errorBody"
                    )
                    return@withContext null
                }
            } catch (e: IOException) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "Network error correcting exercise",
                    e
                )
                return@withContext null
            } catch (e: HttpException) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "HTTP exception correcting exercise",
                    e
                )
                return@withContext null
            } catch (e: Exception) {
                Log.e(
                    "ExercisesRepositoryImpl",
                    "Unexpected error correcting exercise",
                    e
                )
                return@withContext null
            }
        }
}