package com.myriam.projetfinal.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.models.Exercise
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val exoRepository: ExercisesRepository
) : ViewModel() {
    // Observe the StateFlow from the repository
    val devPick: StateFlow<List<Exercise>> = exoRepository.devPick

    // Optional: A separate state to trigger the screen change (if needed)
    private val _shouldNavigate = MutableStateFlow(false)
    val shouldNavigate: StateFlow<Boolean> = _shouldNavigate.asStateFlow()

    // State to hold the filtered dev picks for the UI
    private val _filteredDevPick = MutableStateFlow<List<Exercise>>(emptyList())
    val filteredDevPick: StateFlow<List<Exercise>> = _filteredDevPick.asStateFlow()

    var selectedDevPick: Exercise? = null

    init {
        // Initialize the filtered list with the initial dev picks
        _filteredDevPick.value = exoRepository.devPick.value

        // Observe changes to devPick from the repository and update the filtered list
        viewModelScope.launch {
            exoRepository.devPick.collectLatest { exercises ->
                _filteredDevPick.value = exercises
                // You can also trigger navigation here if needed based on the data
                if (exercises.isNotEmpty()) {
                    // _shouldNavigate.value = true
                }
            }
        }
    }

    fun filterDevsPick(query: String) {
        val currentDevPick = exoRepository.devPick.value // Get the latest value
        val filteredList = if (query.isEmpty()) {
            currentDevPick
        } else {
            currentDevPick.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        _filteredDevPick.value = filteredList
    }
}