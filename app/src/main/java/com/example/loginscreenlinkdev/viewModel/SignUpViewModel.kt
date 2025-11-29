package com.example.loginscreenlinkdev.viewModel

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginscreenlinkdev.R

class SignUpViewModel : ViewModel() {
    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val conPassword = mutableStateOf("")
    val rememberMe = mutableStateOf(false)
    val emailError = mutableStateOf<Int?>(null)
    val passwordError = mutableStateOf<Int?>(null)
    val confirmPasswordError = mutableStateOf<Int?>(null)

    fun onSignUpClick(): Boolean {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()
        val isConfirmPasswordValid = validateConfirmPassword()
        return isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    private fun validateEmail(): Boolean {
        return when {
            email.value.isBlank() -> {
                emailError.value = R.string.error_email_required
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
                emailError.value = R.string.error_email_invalid
                false
            }

            else -> {
                emailError.value = null
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        return when {
            password.value.isBlank() -> {
                passwordError.value = R.string.error_password_required
                false
            }

            password.value.length < 6 -> {
                passwordError.value = R.string.error_password_short
                false
            }

            else -> {
                passwordError.value = null
                true
            }
        }
    }

    private fun validateConfirmPassword(): Boolean {
        return when {
            conPassword.value.isBlank() -> {
                confirmPasswordError.value =
                    R.string.error_confirm_password_required
                false
            }

            conPassword.value != password.value -> {
                confirmPasswordError.value =
                    R.string.error_passwords_do_not_match
                false
            }

            else -> {
                confirmPasswordError.value = null
                true
            }
        }
    }
}
