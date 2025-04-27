package com.myriam.projetfinal.screens.signup_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// Import UserRepository
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay // For simulation

// Add UserRepository to constructor
class SignupScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    // --- Keep existing LiveData & change handlers ---
    private val _username = MutableLiveData<String>("")
    val username: LiveData<String> = _username
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password
    private val _confirmPassword = MutableLiveData<String>("")
    val confirmPassword: LiveData<String> = _confirmPassword
    private val _isSignupEnabled = MutableLiveData<Boolean>(false)
    val isSignupEnabled: LiveData<Boolean> = _isSignupEnabled
    private val _signupError = MutableLiveData<String>("")
    val signupError: LiveData<String> = _signupError

    fun onUsernameChanged(newUsername: String) { /* ...as before... */
        _username.value = newUsername
        validateSignupForm()
    }
    fun onEmailChanged(newEmail: String) { /* ...as before... */
        _email.value = newEmail
        validateSignupForm()
    }
    fun onPasswordChanged(newPassword: String) { /* ...as before... */
        _password.value = newPassword
        validateSignupForm()
    }
    fun onConfirmPasswordChanged(newConfirmPassword: String) { /* ...as before... */
        _confirmPassword.value = newConfirmPassword
        validateSignupForm()
    }
    private fun validateSignupForm() { /* ...as before... */
        val isUsernameValid = _username.value?.isNotEmpty() == true
        val isEmailValid = _email.value?.isNotEmpty() == true && android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value ?: "").matches()
        val isPasswordValid = (_password.value?.length ?: 0) >= 6
        val doPasswordsMatch = _password.value == _confirmPassword.value && _password.value?.isNotEmpty() == true

        _isSignupEnabled.value = isUsernameValid && isEmailValid && isPasswordValid && doPasswordsMatch
    }


    fun onSignupClicked(onSignupSuccess: () -> Unit) {
        if (_isSignupEnabled.value != true) return // Check enabled state

        viewModelScope.launch {
            try {
                _signupError.value = "" // Clear error
                // Placeholder: Normally call a signup endpoint here.
                // For now, we simulate success and immediately log the user in.
                val fakeSignupSuccess = true // Replace with actual backend call later
                delay(500) // Simulate signup network call

                if (fakeSignupSuccess) {
                    // --- Replace this with your backend call later ---
                    // For testing, we'll use the entered email/pass to log in via the repo
                    // You might want dedicated repo.signup() later.
                    val loggedIn = userRepository.login(_email.value!!, _password.value!!)

                    if (loggedIn) {
                        onSignupSuccess() // Navigate on success
                    } else {
                        // This case might happen if repo.login has specific test credentials
                        _signupError.value = "Signup ok, but failed to auto-login."
                    }
                } else {
                    _signupError.value = "Failed to create account (Simulation)"
                }
            } catch (e: Exception) {
                _signupError.value = "Signup failed: ${e.message}"
            }
        }
    }

    // Old performSignup is removed/replaced
}