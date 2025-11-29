package com.example.loginscreenlinkdev.viewModel

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginscreenlinkdev.R

class LoginViewModel : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val rememberMe = mutableStateOf(false)

    val emailError = mutableStateOf<Int?>(null)
    val passwordError = mutableStateOf<Int?>(null)

    fun validateEmail(): Boolean {
        return email.value.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun validatePassword(): Boolean {
        return password.value.length >= 6
    }

    fun onLoginClick(): Boolean {
        val isEmailValid = validateEmail().also {
            emailError.value = when {
                email.value.isBlank() -> R.string.error_email_required
                !it -> R.string.error_email_invalid
                else -> null
            }
        }

        val isPasswordValid = validatePassword().also {
            passwordError.value = when {
                password.value.isBlank() -> R.string.error_password_required
                !it -> R.string.error_password_short
                else -> null
            }
        }

        return isEmailValid && isPasswordValid
    }


}

