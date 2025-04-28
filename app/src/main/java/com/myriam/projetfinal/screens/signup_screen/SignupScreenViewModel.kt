package com.myriam.projetfinal.screens.signup_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.launch

class SignupScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

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

    fun onUsernameChanged(newUsername: String) {
        _username.value = newUsername
        validateSignupForm()
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        validateSignupForm()
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        validateSignupForm()
    }

    fun onConfirmPasswordChanged(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
        validateSignupForm()
    }

    private fun validateSignupForm() {
        val isUsernameValid = _username.value?.isNotEmpty() == true
        val isEmailValid = _email.value?.isNotEmpty() == true && android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value ?: "").matches()
        val isPasswordValid = (_password.value?.length ?: 0) >= 8 && _password.value?.any { it.isLetter() } == true && _password.value?.any { !it.isLetterOrDigit() } == true
        val doPasswordsMatch = _password.value == _confirmPassword.value && _password.value?.isNotEmpty() == true

        _isSignupEnabled.value = isUsernameValid && isEmailValid && isPasswordValid && doPasswordsMatch
    }

    fun onSignupClicked(onSignupSuccess: () -> Unit) {
        if (_isSignupEnabled.value != true) return

        viewModelScope.launch {
            try {
                _signupError.value = ""
                val isSignupSuccessful = userRepository.register(
                    _username.value ?: "",
                    _email.value ?: "",
                    _password.value ?: ""
                )

                if (isSignupSuccessful) {
                    Log.d("SignupViewModel", "Signup successful")
                    onSignupSuccess()
                } else {
                    _signupError.value = "Failed to create account. Username or email might be taken, or password doesn't meet requirements."
                }
            } catch (e: Exception) {
                _signupError.value = "Signup failed: ${e.message}"
                Log.e("SignupViewModel", "Signup error", e)
            }
        }
    }
}