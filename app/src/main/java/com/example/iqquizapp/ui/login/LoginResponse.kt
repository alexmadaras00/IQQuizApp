package com.example.iqquizapp.ui.login

import com.example.iqquizapp.models.User

data class LoginResponse(val error: Boolean,val message: String, val user: User)