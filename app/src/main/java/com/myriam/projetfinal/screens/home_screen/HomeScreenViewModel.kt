package com.myriam.projetfinal.screens.home_screen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.R
import com.myriam.projetfinal.data.repositories.ExercisesRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository

class HomeScreenViewModel(
    private val exoRepository: ExercisesRepositoryImpl
): ViewModel() {
    val devsPick = exoRepository.getDevPick()
    val devPick = MutableLiveData<List<Exercise>>()
    var selectedDevPick: Exercise? = null

    init {
        devPick.value = devsPick
    }

    fun filterDevsPick(query: String) {
        val filteredList = if (query.isEmpty()) {
            devsPick
        } else {
            devsPick.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        devPick.value = filteredList
    }
}