package com.example.iqquizapp.ui.login

import android.content.Context
import android.provider.Telephony.Carriers.PASSWORD
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

data class LoginValidator(
    val context: Context,
    val email: TextInputEditText,
    val password: TextInputEditText,
    val user: EditText?,
    val lastname: EditText?,
    val repeatPassword: EditText?
) {
    constructor(context: Context, email: TextInputEditText, password: TextInputEditText) : this(
        context,
        email,
        password,
        null,
        null,
        null
    )

    lateinit var emailInput: String
    lateinit var passwordInput: String
    lateinit var userInput: String
    lateinit var repeatPasswordInput: String
    fun validateEmail(): Boolean {
        emailInput = email.text.toString().trim()
        if (emailInput.isEmpty()) {
            Toast.makeText(context, "Please enter your e-mail address.", Toast.LENGTH_SHORT).show()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(context, "Invalid e-mail address.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        passwordInput = password.text.toString().trim()
        if (passwordInput.isEmpty()) {
            Toast.makeText(context, "Please enter your password.", Toast.LENGTH_SHORT).show()
            return false
        } else if (passwordInput.length < 5) {
            Toast.makeText(context, "Password too short", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun validateUsername(): Boolean {
        userInput = user?.text.toString().trim()
        if (userInput.isEmpty()) {
            Toast.makeText(context, "Please enter an email.", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInput.length < 2) {
            Toast.makeText(context, "Username too short", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }
    fun validateLastName(): Boolean {
        userInput = user?.text.toString().trim()
        if (userInput.isEmpty()) {
            Toast.makeText(context, "Please enter an email.", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInput.length < 2) {
            Toast.makeText(context, "Username too short", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    fun validateRepeatPassword(): Boolean {
        repeatPasswordInput = repeatPassword?.text.toString().trim()
        if (repeatPasswordInput.isEmpty()) {
            Toast.makeText(context, "You have to fill this field.", Toast.LENGTH_SHORT).show()
            return false
        } else if (repeatPasswordInput != passwordInput) {
            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
