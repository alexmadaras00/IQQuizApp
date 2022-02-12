package com.example.iqquizapp.repository.database

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    var test1: Int,
    var test2: Int,
    var test3: Int,
    var test1_progress: Int,
    var test2_progress: Int,
    var test3_progress: Int,
    var test1_done: Boolean,
    var test2_done: Boolean,
    var test3_done: Boolean
) {

}
