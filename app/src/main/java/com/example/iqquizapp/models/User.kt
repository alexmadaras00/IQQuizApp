package com.example.iqquizapp.models

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val test1: Int,
    val test2: Int,
    val test3: Int,
    val test1_progress: Int,
    val test2_progress: Int,
    val test3_progress: Int
) {

}
