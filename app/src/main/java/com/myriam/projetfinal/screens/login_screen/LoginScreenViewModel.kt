package com.myriam.projetfinal.screens.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// Import UserRepository
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.launch

// Add UserRepository to constructor
class LoginScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    // --- Keep existing LiveData (_email, _password, _isLoginEnabled, _loginError) ---
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password
    private val _isLoginEnabled = MutableLiveData<Boolean>(false)
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled
    private val _loginError = MutableLiveData<String>("")
    val loginError: LiveData<String> = _loginError

    // --- Keep existing onEmailChanged, onPasswordChanged, validateLoginForm ---
    fun onEmailChanged(newEmail: String) { /* ...as before... */
        _email.value = newEmail
        validateLoginForm()
    }
    fun onPasswordChanged(newPassword: String) { /* ...as before... */
        _password.value = newPassword
        validateLoginForm()
    }
    private fun validateLoginForm() { /* ...as before... */
        val isEmailValid = _email.value?.isNotEmpty() == true && android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value ?: "").matches()
        val isPasswordValid = _password.value?.length ?: 0 >= 6
        _isLoginEnabled.value = isEmailValid && isPasswordValid
    }

    fun onLoginClicked(onLoginSuccess: () -> Unit) {
        // Ensure login isn't attempted if button isn't enabled
        if (_isLoginEnabled.value != true) return

        viewModelScope.launch {
            try {
                _loginError.value = "" // Clear error
                // Call the repository's login method
                val isLoginSuccessful = userRepository.login(
                    _email.value ?: "",
                    _password.value ?: ""
                )

                if (isLoginSuccessful) {
                    // Let the UI handle navigation
                    onLoginSuccess()
                } else {
                    _loginError.value = "Invalid email or password" // Error from repo simulation
                }
            } catch (e: Exception) {
                // Handle potential exceptions during the repo call
                _loginError.value = "Login failed: ${e.message}"
            }
        }
    }

    // The old performLogin is removed, logic is now in UserRepositoryImpl
}