package com.example.iqquizapp.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {


    // A placeholder email validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}