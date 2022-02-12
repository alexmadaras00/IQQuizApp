package com.example.iqquizapp.ui.login

import com.example.iqquizapp.repository.database.User

data class LoginResponse(val error: Boolean,val message: String, val user: User)